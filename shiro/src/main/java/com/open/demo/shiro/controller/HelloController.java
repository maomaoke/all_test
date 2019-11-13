package com.open.demo.shiro.controller;

import com.open.demo.shiro.pojo.dto.request.LoginParam;
import com.open.demo.shiro.pojo.dto.response.UsersDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-11-13-4:13 下午
 */
@RestController
public class HelloController {

    @PostMapping("/login")
    public UsersDTO login(@RequestBody LoginParam param) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(param.getUsername(), param.getPassword());
        subject.login(token);
        return null;
    }
}
