package com.open.demo.shiro.config;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Deque;
import java.util.Objects;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-11-19-8:26 下午
 */
public class DefaultSessionListener implements SessionListener {

    private RedisTemplate<String, Deque<Serializable>> kickOutRedisTemplate;

    public DefaultSessionListener(RedisTemplate<String, Deque<Serializable>> redisTemplate) {
        this.kickOutRedisTemplate = redisTemplate;
    }

    @Override
    public void onStart(Session session) {
    }

    @Override
    public void onStop(Session session) {
        //删除kick-out
        deleteKickOut(session);
    }

    @Override
    public void onExpiration(Session session) {
        //删除kick-out
        deleteKickOut(session);
    }

    private void deleteKickOut(Session session) {
        //删除kickout
        String username = (String) session.getAttribute("username");
        if (!StringUtils.isEmpty(username)) {
            Deque<Serializable> sessionIdDeque = kickOutRedisTemplate.opsForValue().get("kick_out:" + username);
            if (Objects.nonNull(sessionIdDeque) && sessionIdDeque.size() > 0) {
                sessionIdDeque.remove(session.getId());
            }
            if (Objects.isNull(sessionIdDeque) || sessionIdDeque.size() == 0) {
                kickOutRedisTemplate.delete("kick_out:" + username);
            } else {
                kickOutRedisTemplate.opsForValue().set("kick_out:", sessionIdDeque);
            }
        }
    }
}
