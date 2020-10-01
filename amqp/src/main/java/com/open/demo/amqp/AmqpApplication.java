package com.open.demo.amqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chenkechao
 * @date 2020/9/29 1:58 下午
 */
@SpringBootApplication
public class AmqpApplication {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private RabbitAdmin rabbitAdmin;

    public static void main(String[] args) {
        SpringApplication.run(AmqpApplication.class, args);
    }
}
