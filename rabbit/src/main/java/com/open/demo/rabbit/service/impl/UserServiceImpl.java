package com.open.demo.rabbit.service.impl;

import com.open.demo.rabbit.config.RabbitConfig;
import com.open.demo.rabbit.entity.User;
import com.open.demo.rabbit.mapper.UserMapper;
import com.open.demo.rabbit.service.UserService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author chenkechao
 * @date 2020/8/28 10:38 上午
 */
@Transactional(rollbackFor = {Throwable.class})
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final AmqpTemplate amqpTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public UserServiceImpl(UserMapper userMapper, RabbitTemplate amqpTemplate) {
        this.userMapper = userMapper;
        this.amqpTemplate = amqpTemplate;
    }

    @Override
    public int createUser(String username) {

        User user = new User();
        user.setName(username);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        userMapper.insert(user);

        rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE_NAME, "foo.bar.baz", user.toString());

        throwException();

        return 0;
    }

    private void throwException() {
        throw new  RuntimeException("抛异常");
    }
}
