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

    @PostMapping("/login.do")
    public Object register(String email, String password){
        return userService.checkLogin(email, password);
    }

}
