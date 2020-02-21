package com.practice.file_management_sys.controller;

import com.practice.file_management_sys.domain.JsonData;
import com.practice.file_management_sys.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    @GetMapping("/login.do")
    public Object login(HttpServletRequest request){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(email + " " + password);
        JsonData result = userService.checkLogin(email, password);
        if (result.getCode() == 200){
            request.getSession().setAttribute("account", email);
        }
        return result;
    }
    
    /***
     * @Author Saul
     * @Description  退出登录，使session失效
     * @Date 3:20 PM 11/2/20
     * @param session HttpSession
     * @return {@link {@link JsonData}}
     */
    @GetMapping("/logout.do")
    public Object logout(HttpSession session){
        session.invalidate();
        return JsonData.buildSuccess();
    }

}
