package com.practice.file_management_sys.controller;

import com.practice.file_management_sys.domain.JsonData;
import com.practice.file_management_sys.service.MailService;
import com.practice.file_management_sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource("classpath:config/email.properties")
@RequestMapping("/user")
public class UserRegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Value("${email.subject}")
    private String subject;

    @Value("${email.content}")
    private String content;


    @PostMapping("/register.do")
    public Object register(String email, String gender, String password, String domain){
        JsonData jsonData = (JsonData) userService.addUser(email, gender, password, domain);
        if (jsonData.getCode() == 0){
            mailService.sendSimpleEmail(email, subject, content);
        }
        return  jsonData;
    }
}
