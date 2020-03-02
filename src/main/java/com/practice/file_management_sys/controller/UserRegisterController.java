package com.practice.file_management_sys.controller;

import com.practice.file_management_sys.domain.JsonData;
import com.practice.file_management_sys.domain.User;
import com.practice.file_management_sys.enumClass.StateType;
import com.practice.file_management_sys.service.MailService;
import com.practice.file_management_sys.service.UserService;
import exception.BusinessException;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "用户管理")
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
    @ApiOperation(value = "发送注册验证码", notes = "异步邮件任务发送验证码")
    @ApiImplicitParam(name = "email", value = "用户传入的合法邮箱地址", required = true,paramType = "query",dataType = "字符串")
    @ApiResponse(code = 202, message = "邮件发送成功",response = JsonData.class)
    public JsonData sendVerificationCode(@RequestParam String email){
        mailService.sendVerificationCodeEmail(email);
        return JsonData.buildSuccess(StateType.ACCEPTED.getCode(), StateType.ACCEPTED.value());
    }

    @PostMapping("/register.do")
    @ApiOperation(value = "用户注册", notes = "用户校验邮箱验证码和注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "user对象", required = true, paramType = "body",dataType = "User对象"),
            @ApiImplicitParam(name = "verificationCode", value = "邮箱发送的验证码", required = true, paramType = "query",dataType = "字符串")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = User.class),
            @ApiResponse(code = 500, message = "服务器内部错误", response = JsonData.class)
    })
    public JsonData register(@RequestBody User user, String verificationCode) throws BusinessException {
        JsonData jsonData = userService.register(user,verificationCode);
        if (jsonData.getCode() == 0){
            mailService.sendWelcomeEmail(user.getEmail(), subject, content);
        }
        return  jsonData;
    }
}
