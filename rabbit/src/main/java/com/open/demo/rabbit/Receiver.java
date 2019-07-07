package com.open.demo.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author chenkechao
 * @date 2019-07-02 21:29
 */
@Component
/*
 * 制定监听队列
 */
@RabbitListener(queues = {"hello"})
public class Receiver {

    @RabbitHandler
    public void process(String msg) {
            System.out.println("接受者介绍到消息:" + msg);
    }
}
