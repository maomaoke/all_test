package com.open.demo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author chenkechao
 * @date 2019/10/1 12:43 下午
 */
@EnableSwagger2
@SpringBootApplication
public class SpringFeatureApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringFeatureApplication.class, args);
    }
}
