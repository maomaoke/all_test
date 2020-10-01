package com.open.demo.rabbit;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author chenkechao
 * @date 2019-07-02 21:29
 */
//@Component
/*
 * 指定监听队列
 */
@RabbitListener(queuesToDeclare = {@Queue("hello")})
public class Receiver {

    public void receiveMessage(String msg) {
            System.out.println("接受者介绍到消息:" + msg);
    }
}
