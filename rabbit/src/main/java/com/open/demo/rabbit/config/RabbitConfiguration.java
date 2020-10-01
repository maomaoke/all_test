package com.open.demo.rabbit.config;

import com.open.demo.rabbit.Receiver;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenkechao
 * @date 2020/9/29 1:43 下午
 */
@Configuration
public class RabbitConfiguration {

    private static final String TEXT1_QUEUE = "queue-text1";

    private static final String TEXT1_EXCHANGE = "exchange-text1";

    @Bean
    Queue text1Queue() {
        return new Queue(TEXT1_QUEUE, true);
    }

    @Bean
    TopicExchange text1Exchange() {
        return new TopicExchange(TEXT1_EXCHANGE, true, false);
    }

    @Bean
    Binding text1Binding(Queue text1Queue, TopicExchange text1Exchange) {
        return BindingBuilder.bind(text1Queue).to(text1Exchange).with("text.#");
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

}
