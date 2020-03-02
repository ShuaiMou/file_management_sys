package com.practice.file_management_sys.service.serviceImpl;


import com.practice.file_management_sys.domain.JsonData;
import com.practice.file_management_sys.domain.User;
import com.practice.file_management_sys.enumClass.StateType;
import com.practice.file_management_sys.mapper.UserMapper;
import com.practice.file_management_sys.service.UserService;
import com.practice.file_management_sys.utils.EncriptionUtils;
import com.practice.file_management_sys.utils.RedisUtils;
import com.practice.file_management_sys.exception.BusinessException;
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
    public JsonData checkLogin(String email, String password){
        User user = userMapper.findByEmail(email);

    // check email
    if (null == user){
        throw new BusinessException(StateType.UNAUTHORIZED.getCode(), "the email does not exits");
    }

    //check password
    String psw = EncriptionUtils.EncoderByMD5(password);
    if (!user.getPassword().equals(psw)){
        throw new BusinessException(StateType.UNAUTHORIZED.getCode() , "wrong password ");
    }
    return JsonData.buildSuccess(user);
    }

    /**
     * 功能：完成用户注册，将信息验证并存入数据库
     *
     * @param user user对象
     * @param verificationCode 注册验证码
     * @return JsonData注册成功或者失败
     */
    @Override
    public JsonData register(User user, String verificationCode) throws BusinessException {
        User tempUser = userMapper.findByEmail(user.getEmail());

        //check user
        if ( null != tempUser) {//exits
            throw new BusinessException(StateType.UNAUTHORIZED.getCode(),"the email has registered");
        }else if(verificationCode == null || !verificationCode.equalsIgnoreCase((String) redisUtils.get(user.getEmail()))){
            throw new BusinessException(StateType.UNAUTHORIZED.getCode(),"验证码不正确，请重试");
        } else {
            //add user
            user.setPassword(EncriptionUtils.EncoderByMD5(user.getPassword()));
            user.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            user.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            //store in database
            return JsonData.buildSuccess(user);
        }
    }

    /**
     * 功能：更新用户个人信息
     *
     * @param user 用户对象
     * @return JsonData
     */
    @Override
    public JsonData updatePersonalInfo(User user) {
        userMapper.updateInformation(user);
        return JsonData.buildSuccess(userMapper.findByEmail(user.getEmail()));
    }
}
