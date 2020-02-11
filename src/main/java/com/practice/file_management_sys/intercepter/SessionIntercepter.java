package com.practice.file_management_sys.intercepter;

import com.practice.file_management_sys.domain.JsonData;
import com.practice.file_management_sys.enumClass.StateType;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

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
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Type", "application/json");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Max-Age", "3600");

            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer=null;
            try {
                writer=response.getWriter();
                writer.write(JsonData.buildError(StateType.NOT_LOGIN.getCode(), StateType.NOT_LOGIN.value()).toString());
                writer.flush();
            }finally {
                if(writer != null){
                    writer.close();
                }
            }

            return false;
        }
        return true;
    }
}
