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
}
