package com.practice.file_management_sys.logs;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: Saul
 * @Date: 3/3/20 9:48 上午
 * @Description:
 */
@Component
@Aspect
public class LogAspectHandler {

    @Autowired(required=false)
    HttpServletRequest request;

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspectHandler.class);

    /***
     * @Author Saul
     * @Description  TODO: 定义切面，拦截com.practice.file_management_sys.controller包下所有方法
     * @Date 9:53 上午 3/3/20
     */
    @Pointcut("execution(* com.practice.file_management_sys.controller..*.*(..))")
    public void controllerPointCut(){}


    /***
     * @Author Saul
     * @Description  TODO: 定义切面，拦截com.practice.file_management_sys.service包下所有方法
     * @Date 9:53 上午 3/3/20
     */
    @Pointcut("execution(* com.practice.file_management_sys.service..*.*(..))")
    public void servicePointCut(){}

    @Before("controllerPointCut()")
    public void dobefore(JoinPoint joinPoint){
        LOGGER.info("=======dobefore进入日志切面前置通知======");
        Date begainTime = new Date();
        //debug状态记录
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("开始记时: {}  URL: {}",
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(begainTime),
                    request.getRequestURL());
        }

        Signature signature = joinPoint.getSignature();
        String packageName = signature.getDeclaringTypeName();
        String functionName = signature.getName();

        LOGGER.info("即将执行的方法为: {}, 属于包: {}",functionName, packageName);
        LOGGER.info("用户请求的URL为: {}, IP地址为: {}", request.getRequestURL(), request.getRemoteAddr());
    }

    @After("controllerPointCut()")
    public void doAfter(JoinPoint joinPoint){
        LOGGER.info("=======doAftere进入日志切面后置通知======");
        Date endTime = new Date();
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("计时结束: {}  URL: {}",
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(endTime),
                    request.getRequestURL());
        }
        LOGGER.info("方法{}已经执行完", joinPoint.getSignature().getName());

    }

    @AfterReturning(pointcut = "controllerPointCut()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result){
        LOGGER.info("方法{}执行完毕, 返回参数为:{}", joinPoint.getSignature().getName(),result);
    }

    @AfterThrowing(pointcut = "controllerPointCut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e){
        LOGGER.error("执行方法 {} 抛出异常 {}",joinPoint.getSignature().getName(), e.getMessage());
    }





}
