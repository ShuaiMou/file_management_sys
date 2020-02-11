package com.practice.file_management_sys.controller;

import com.practice.file_management_sys.domain.JsonData;
import com.practice.file_management_sys.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserLoginController {
    @Resource
    private UserService userService;

    /**
     * 功能：登录验证
     *
     * @param request HttpServletRequest
     * @return JsonData 登录成功或者失败
     */
    @PostMapping("/login.do")
    public Object register(HttpServletRequest request){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        JsonData result = userService.checkLogin(email, password);
        if (result.getCode() == 200){
            request.getSession().setAttribute("account", email);
        }
        return result;
    }

}
