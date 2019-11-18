package com.open.demo.shiro.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-11-18-1:06 下午
 */
@Component
public class LoginSuccessEventPublisher {

    private ApplicationEventPublisher applicationEventPublisher;

    public LoginSuccessEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void loginSuccessNotification(HttpServletRequest request, HttpServletResponse response) {
        //可以添加分布式锁
        LoginSuccessEvent event = new LoginSuccessEvent(this, request, response);
        applicationEventPublisher.publishEvent(event);
    }

}
