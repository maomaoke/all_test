package com.open.demo.rabbit.client;

import ch.qos.logback.core.util.TimeUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author chenkechao
 * @date 2020/8/4 3:39 下午
 */
public class RabbitConsumer {
    private static final String QUEUE_NAME = "queue_demo";
    private static final String IP_ADDRESS = "192.168.124.59";
    private static final int port = 5672;

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Address[] addresses = {new Address(IP_ADDRESS, port)};


        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("root");
        factory.setPassword("123456");

        Connection connection = factory.newConnection(addresses);
        Channel channel = connection.createChannel();
        //设置客户端最多接收未被ack的消息的个数
        channel.basicQos(64);
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                System.out.println("小可爱收到发来的消息: " + new String(body));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(QUEUE_NAME, consumer);
        TimeUnit.SECONDS.sleep(5);
        channel.close();
        connection.close();
    }
}
