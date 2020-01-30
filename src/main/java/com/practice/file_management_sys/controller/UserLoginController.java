package com.practice.file_management_sys.controller;

import com.practice.file_management_sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login.do")
    public Object register(String email, String password){
        return userService.checkLogin(email, password);
    }

}
