package com.practice.file_management_sys.service.serviceImpl;


import com.practice.file_management_sys.domain.JsonData;
import com.practice.file_management_sys.domain.User;
import com.practice.file_management_sys.mapper.UserMapper;
import com.practice.file_management_sys.service.UserService;
import com.practice.file_management_sys.utils.EncriptionUtils;
import com.practice.file_management_sys.utils.RedisClientUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisClientUtils redisUtils;

    /**
     * 功能：验证用户密码和账号是否匹配
     *
     * @param email 客户端传入到邮箱，也就是登录账户
     * @param password 密码
     * @return JsonData 登录成功还是失败
     */
    @Override
    public JsonData checkLogin(String email, String password) {
        User user = userMapper.findByEmail(email);

        // check email
        if (null == user){
            return JsonData.buildError("the email does not exits", -1);
        }

        //check password
        String psw = EncriptionUtils.EncoderByMD5(password);
        if (!user.getPassword().equals(psw)){
            return JsonData.buildError("wrong password " , -1);
        }

        return JsonData.buildSuccess(user, 0);
    }

    /**
     * 功能：完成用户注册，将信息验证并存入数据库
     *
     * @param email 邮箱
     * @param gender 性别
     * @param password 密码
     * @param domain 工作领域
     * @param verificationCode 注册验证码
     * @return JsonData注册成功或者失败
     */
    @Override
    public JsonData register(String email, String gender, String password, String domain, String verificationCode){
        User user = userMapper.findByEmail(email);

        //check user
        if ( null != user) {//exits
            return JsonData.buildError("the email has registered", -1);
        }else if(verificationCode == null || !verificationCode.equalsIgnoreCase(redisUtils.get(email))){
            return JsonData.buildError("验证码不正确，请重试", -1);
        } else {
            //add user
            user = new User();
            user.setGender(gender);
            user.setEmail(email);
            user.setPassword(EncriptionUtils.EncoderByMD5(password));
            user.setDomain(domain);

            //store in database
            userMapper.addUser(user);

            //set the returned data
            return JsonData.buildSuccess("register successful", 0);
        }
    }

    /**
     * 功能：更新用户个人信息
     *
     * @param email 邮箱
     * @param gender 性别
     * @param domain 工作领域
     * @return JsonData
     */
    @Override
    public JsonData updatePersonalInfo(String email, String gender, String domain) {
        int n = userMapper.updateInformation(email, gender, domain);
        if (n == 1){
            return JsonData.buildSuccess();
        }else {
            return JsonData.buildError("更新失败");
        }


    }
}
