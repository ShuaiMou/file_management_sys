package com.practice.file_management_sys.controller;

import com.practice.file_management_sys.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class PersonalInformationController {

    @Resource
    private UserService userService;

    /**
     * 功能：根据账户更新个人信息
     *
     * @param email 账户邮箱
     * @param gender 性别
     * @param domain 工作领域
     * @return jsondata
     */
    @GetMapping("/update")
    public Object updateInfo(String email, String gender, String domain){
        return userService.updatePersonalInfo(email, gender, domain);
    }
}
