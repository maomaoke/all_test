package com.open.demo.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-11-13-10:39 上午
 */
@MapperScan("com.open.demo.shiro.dao")
@SpringBootApplication
public class ShiroApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class, args);
    }
}
