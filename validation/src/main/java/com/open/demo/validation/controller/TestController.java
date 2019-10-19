package com.open.demo.validation.controller;

import com.open.demo.validation.pojo.param.HelloParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-10-17-4:19 下午
 */
@RestController
@Validated
public class TestController {

    @PostMapping("/hello")
    public String hello(@RequestBody HelloParam param) {
        return "hello";
    }

    @GetMapping("/hello/{id}")
    public String hello(@PathVariable @Min(value = 100) Integer id) {
        return id.toString();
    }
}
