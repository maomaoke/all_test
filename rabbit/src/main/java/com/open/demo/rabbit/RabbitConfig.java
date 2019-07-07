package com.open.demo.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenkechao
 * @date 2019-07-02 21:31
 */
@Configuration
public class RabbitConfig {

    /**
     * 注册 hello 队列
     * @return
     */
    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }

}
