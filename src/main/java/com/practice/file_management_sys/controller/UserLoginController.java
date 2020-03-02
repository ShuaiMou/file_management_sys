package com.practice.file_management_sys.controller;

import com.practice.file_management_sys.domain.JsonData;
import com.practice.file_management_sys.domain.User;
import com.practice.file_management_sys.enumClass.StateType;
import com.practice.file_management_sys.service.UserService;
import exception.BusinessException;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserLoginController {
    @Resource
    private UserService userService;

    @PostMapping("/login.do")
    @ApiOperation(value = "登录验证",notes = "传入用户名和密码登录系统")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "用户唯一ID", required = true, paramType = "query", dataType = "字符串"),
            @ApiImplicitParam(name = "password", value = "用户登录密码", required = true, paramType = "query", dataType = "字符串"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response = User.class),
            @ApiResponse(code = 401, message = "用户名与密码不一致或用户不存在",response = JsonData.class)
    })
    public JsonData login(HttpServletRequest request){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        JsonData result = userService.checkLogin(email, password);
        if (result.getCode() == 200){
            request.getSession().setAttribute("account", email);
        }
        return result;
    }
    

    @DeleteMapping("/logout.do")
    @ApiOperation(value = "退出登录", notes = "退出用户登录，清除session")
    @ApiResponse(code = 204, message = "no content", response = JsonData.class)
    public JsonData logout(HttpSession session){
        session.invalidate();
        return JsonData.buildSuccess(StateType.NO_CONTENT.getCode(),StateType.NO_CONTENT.value());
    }

}
