package com.practice.file_management_sys.service.serviceImpl;


import com.practice.file_management_sys.domain.JsonData;
import com.practice.file_management_sys.domain.User;
import com.practice.file_management_sys.enumClass.StateType;
import com.practice.file_management_sys.mapper.UserMapper;
import com.practice.file_management_sys.service.UserService;
import com.practice.file_management_sys.utils.EncriptionUtils;
import com.practice.file_management_sys.utils.RedisUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisUtils redisUtils;

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
            return JsonData.buildError(StateType.UNAUTHORIZED.getCode(), "the email does not exits");
        }

        //check password
        String psw = EncriptionUtils.EncoderByMD5(password);
        if (!user.getPassword().equals(psw)){
            return JsonData.buildError(StateType.UNAUTHORIZED.getCode() , "wrong password ");
        }

        return JsonData.buildSuccess(user);
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
    public JsonData register(String email, String gender, String password, String domain,String username, String verificationCode){
        User user = userMapper.findByEmail(email);

        //check user
        if ( null != user) {//exits
            return JsonData.buildError(StateType.UNAUTHORIZED.getCode(), "the email has registered");
        }else if(verificationCode == null || !verificationCode.equalsIgnoreCase((String) redisUtils.get(email))){
            return JsonData.buildError(StateType.UNAUTHORIZED.getCode(), "验证码不正确，请重试");
        } else {
            //add user
            user = new User();
            user.setGender(gender);
            user.setEmail(email);
            user.setPassword(EncriptionUtils.EncoderByMD5(password));
            user.setDomain(domain);
            user.setUsername(username);
            user.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            user.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            //store in database
            userMapper.addUser(user);

            //set the returned data
            return JsonData.buildSuccess();
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
    public JsonData updatePersonalInfo(String email, String gender, String domain, String password) {
        int n = userMapper.updateInformation(email, gender, domain, password);
        if (n == 1){
            return JsonData.buildSuccess();
        }else {
            return JsonData.buildError(StateType.PROCESSING_EXCEPTION.getCode(),StateType.PROCESSING_EXCEPTION.value());
        }


    }
}
