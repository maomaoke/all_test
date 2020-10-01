package com.open.demo.rabbit.config;

import com.open.demo.rabbit.Receiver;
import com.rabbitmq.client.ShutdownSignalException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.transaction.TransactionManager;

import javax.annotation.PostConstruct;

/**
 * @author chenkechao
 * @date 2019-07-02 21:31
 */
//@Configuration
public class RabbitConfig {

    public static final String TOPIC_EXCHANGE_NAME = "spring-boot-exchange";

    public static final String QUEUE_NAME = "spring-boot";

    @Autowired
    private RabbitTemplate rabbitTemplate;

//    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {

        CachingConnectionFactory cachingConnectionFactory = (CachingConnectionFactory) connectionFactory;
        //开启 return callback. return callback 会在 存在Exchange 但是不存在 queue时,进行回调
        cachingConnectionFactory.setPublisherReturns(true);
        //开启 confirm callback.
        cachingConnectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);

        connectionFactory.addConnectionListener(new ConnectionListener() {
            @Override
            public void onCreate(Connection connection) {
            }

            @Override
            public void onClose(Connection connection) {
            }

            @Override
            public void onShutDown(ShutdownSignalException signal) {
            }
        });

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        rabbitTemplate.setMandatory(true);

        //默认的最大重试次数是3
        RetryTemplate retryTemplate = new RetryTemplate();
        //回退策略 控制在重试时的回退操作
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        retryTemplate.setBackOffPolicy(backOffPolicy);

        rabbitTemplate.setRetryTemplate(retryTemplate);
        //RecoveryCallback 会在所有尝试完成之后 进行回调, 错误最终记录回调
        rabbitTemplate.setRecoveryCallback(context -> null);

        //注册 return callback
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {

        });

        rabbitTemplate.invoke(t -> {
//            t.convertAndSend();
//            t.waitForConfirmsOrDie();
            return true;
        });

        return rabbitTemplate;
    }

//    @Bean
    Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

//    @Bean
    TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

//    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
    }

//    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
//        container.setTransactionManager(transactionManager);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }

//    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
//
//    @PostConstruct
//    protected void init() {
//        System.out.println("init方法");
//        rabbitTemplate.setChannelTransacted(true);
//    }

}
