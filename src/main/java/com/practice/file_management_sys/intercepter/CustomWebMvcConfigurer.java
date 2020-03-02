package com.practice.file_management_sys.intercepter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther: Saul
 * @Date: 11/2/20 2:21 PM
 * @Description: 添加一个拦截器
 */
@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionIntercepter()).addPathPatterns("/user/a/b");
//                .excludePathPatterns("/user/login.do").excludePathPatterns("/user/register.do")
//                .excludePathPatterns("/user/send").excludePathPatterns("/index.html");
    }
}
