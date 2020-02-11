package com.practice.file_management_sys.intercepter;

import com.practice.file_management_sys.enumClass.StateType;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: Saul
 * @Date: 11/2/20 2:09 PM
 * @Description: 用户登录检查拦截
 */
public class SessionIntercepter implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object account = request.getSession().getAttribute("account");
        if(null == account){
            response.sendError(StateType.NOT_LOGIN.getCode(), StateType.NOT_LOGIN.value());
            return false;
        }
        return true;
    }
}
