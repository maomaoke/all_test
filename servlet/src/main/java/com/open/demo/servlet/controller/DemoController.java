package com.open.demo.servlet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-12-27-2:42 下午
 */
@RequestMapping("/demo")
@RestController
public class DemoController {

    @GetMapping("/demo1")
    public String demo1() {
        return "demo1";
    }

    @GetMapping("/demo2")
    public String demo2() {
        return "demo2";
    }
}
