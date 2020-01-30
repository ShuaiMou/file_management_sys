package com.practice.file_management_sys.controller;

import com.practice.file_management_sys.domain.JsonData;
import com.practice.file_management_sys.service.MailService;
import com.practice.file_management_sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @RequestMapping("/register.do")
    public Object register(String email, String gender, String password, String domain){
        System.out.println(email + gender + password + domain);
        JsonData jsonData = (JsonData) userService.addUser(email, gender, password, domain);
        if (jsonData.getCode() == 0){
            String subject = "confirmation for registering washing car appointment system ";
            String content = "registered successfully, wecome to  washing car appointment system.";
            mailService.sendSimpleEmail(email, subject, content);
        }
        return  jsonData;
    }
}
