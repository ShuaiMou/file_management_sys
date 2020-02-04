package com.practice.file_management_sys.controller;

import com.practice.file_management_sys.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserLoginController {
    @Resource
    private UserService userService;

    /**
     * 功能：登录验证
     *
     * @param email 接收前端传入的邮件地址
     * @param password 接收前端传入的密码
     * @return JsonData 登录成功或者失败
     */
    @PostMapping("/login.do")
    public Object register(String email, String password){
        return userService.checkLogin(email, password);
    }

}
