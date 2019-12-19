package com.open.demo.shiro.controller;

import com.google.common.collect.Maps;
import com.open.demo.shiro.config.KickOutSessionControlFilter;
import com.open.demo.shiro.event.LoginSuccessEventPublisher;
import com.open.demo.shiro.pojo.dto.request.LoginParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-11-13-4:13 下午
 */
@RestController
public class HelloController {

    @Autowired
    private LoginSuccessEventPublisher loginSuccessEventPublisher;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginParam param,
                                     @ApiIgnore HttpServletRequest request,
                                     @ApiIgnore HttpServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(param.getUsername(), param.getPassword());
        subject.login(token);
        Session session = subject.getSession();
        if (subject.isAuthenticated()) {
            session.touch();
        }

        session.setAttribute("123", "success");

        //发送事件
        loginSuccessEventPublisher.loginSuccessNotification(request, response);
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("code", 0);
        map.put("msg", "success");



        return map;
    }

    @PostMapping("/something")
    public Object getSessionMap() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        return "{\"message\":\"something\"}";
    }

    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "{\"msg\": \"success\"}";
    }

    @GetMapping("/hello")
    public String hello() {
        return "{\"say\":\"hello\"}";
    }

    @GetMapping("/unLogin")
    public String unLogin() {
        return "{\"code\": 1, \"msg\": \"未登录,请重新登录\"}";
    }

    @GetMapping("/unAuthorized")
    public String unAuthorized() {
        return "{\"code\": 1, \"msg\": \"权限不够\"}";
    }

    @GetMapping("/kick-out")
    public String kickOut() {
        return "{\"code\": \"1\", \"msg\": \"您的账号已在别处登录\"}";

    }
}
