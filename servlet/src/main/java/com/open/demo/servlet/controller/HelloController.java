package com.open.demo.servlet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-12-27-2:39 下午
 */
@RequestMapping("/hello/1")
@RestController
public class HelloController {

    @GetMapping("/abc")
    public String abc() {
        return "abc";
    }

    @GetMapping("/efg")
    public String efg() {
        return "efg";
    }
}
