package com.open.demo.rabbit.controller;

import com.open.demo.rabbit.Sender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-08-05-10:16
 */
@RestController
public class TestController {

    private Sender sender;

    public TestController(Sender sender) {
        this.sender = sender;
    }

    @GetMapping("/test")
    public void helloQueue() {
        sender.send();
    }
}
