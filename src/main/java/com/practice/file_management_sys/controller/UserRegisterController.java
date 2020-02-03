package com.practice.file_management_sys.controller;

import com.practice.file_management_sys.domain.JsonData;
import com.practice.file_management_sys.service.MailService;
import com.practice.file_management_sys.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@PropertySource("classpath:config/email.properties")
@RequestMapping("/user")
public class UserRegisterController {

    @Resource
    private UserService userService;

    @Resource
    private MailService mailService;

    @Value("${email.subject}")
    private String subject;

    @Value("${email.content}")
    private String content;

    @GetMapping("/send")
    public Object sendVerificationCode(String email){
        mailService.sendVerificationCodeEmail(email);
        return JsonData.buildSuccess("发送成功", 0);
    }

    @PostMapping("/register.do")
    public Object register(String email, String gender, String password, String domain, String verificationCode){
        JsonData jsonData = userService.register(email, gender, password, domain,verificationCode);
        if (jsonData.getCode() == 0){
            mailService.sendWelcomeEmail(email, subject, content);
        }
        return  jsonData;
    }
}
