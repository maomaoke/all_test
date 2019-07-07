package com.open.demo.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author chenkechao
 * @date 2019-07-02 21:26
 */

@Component
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() {

        String msg = "hello " + new Date().toString();

        System.out.println(msg);
        //将 msg 文本 发送到 'hello'队列中
        amqpTemplate.convertAndSend("hello", msg);

    }
}
