package com.open.demo.rabbit.controller;

import com.open.demo.rabbit.Sender;
import com.open.demo.rabbit.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-08-05-10:16
 */
@RestController
public class TestController {

    private final UserService userService;

    private final Sender sender;

    public TestController(Sender sender, UserService userService) {
        this.sender = sender;
        this.userService = userService;
    }

    @GetMapping("/test/{username}")
    public void helloQueue(@PathVariable("username") String username) {
        sender.send(username);
    }

    @PostMapping("/user/{username}")
    public void createUser(@PathVariable("username") String username) {
        userService.createUser(username);
    }
}
