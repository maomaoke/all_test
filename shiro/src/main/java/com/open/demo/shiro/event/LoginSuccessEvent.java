package com.open.demo.shiro.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-11-18-1:05 下午
 */
@Getter
public class LoginSuccessEvent extends ApplicationEvent {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public LoginSuccessEvent(Object source, HttpServletRequest request, HttpServletResponse response) {
        super(source);
        this.request = request;
        this.response = response;
    }
}
