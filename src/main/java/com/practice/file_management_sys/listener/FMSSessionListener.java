package com.practice.file_management_sys.listener;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Auther: Saul
 * @Date: 4/3/20 7:48 下午
 * @Description:统计在线用户数量
 */
@Component
@Getter
public class FMSSessionListener implements HttpSessionListener {

    private int onlineUserCount;


    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("新用户上线了");
        onlineUserCount++;
        se.getSession().getServletContext().setAttribute("count", onlineUserCount);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("用户下线了");
        onlineUserCount--;
        se.getSession().getServletContext().setAttribute("count", onlineUserCount);
    }
}
