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

    /**
     * 功能：发送验证码到用户邮箱
     *
     * @param email 获取传入的邮件地址
     * @return JsonData 发送完成通知
     */
    @GetMapping("/send")
    public Object sendVerificationCode(String email){
        mailService.sendVerificationCodeEmail(email);
        return JsonData.buildSuccess();
    }

    /**
     * 功能： 用户校验邮箱验证码和注册
     *
     * @param email 邮箱
     * @param gender 性别
     * @param password 注册密码密码
     * @param domain 工作领域
     * @param verificationCode 验证码
     * @return JsonData
     */
    @PostMapping("/register.do")
    public Object register(String email, String gender, String password, String domain, String username, String verificationCode){
        JsonData jsonData = userService.register(email, gender, password, domain,username,verificationCode);
        if (jsonData.getCode() == 0){
            mailService.sendWelcomeEmail(email, subject, content);
        }
        return  jsonData;
    }
}
