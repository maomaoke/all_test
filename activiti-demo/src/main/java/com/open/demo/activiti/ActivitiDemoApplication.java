package com.open.demo.activiti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author chenkechao
 * @date 2020/5/23 2:32 下午
 */
@SpringBootApplication
public class ActivitiDemoApplication {
    public static void main(String[] args) {
        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        SpringApplication.run(ActivitiDemoApplication.class, args);
    }
}
