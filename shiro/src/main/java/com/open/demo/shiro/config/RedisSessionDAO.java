package com.open.demo.shiro.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-11-13-11:46 下午
 */
@Slf4j
public class RedisSessionDAO extends AbstractSessionDAO {

    private RedisTemplate<String, Session> redisTemplate;


    private ShiroSessionProperties shiroSessionProperties;

    private final long expire;

    private static final long DEFAULT_TIMEOUT = 10;

    private final String PREFIX;

    public RedisSessionDAO(RedisTemplate<String, Session> redisTemplate,
                           ShiroSessionProperties shiroSessionProperties) {
        this.redisTemplate = redisTemplate;
        this.shiroSessionProperties = shiroSessionProperties;
        PREFIX = shiroSessionProperties.getPrefix();
        Long expire = shiroSessionProperties.getExpire();
        if (Objects.isNull(expire) || expire < DEFAULT_TIMEOUT) {
            expire = DEFAULT_TIMEOUT;
        }
        this.expire = expire;
    }

    private String getKey(String id) {
        return PREFIX + ":" + id;
    }


    private void saveSession(Session session) {
        if (Objects.nonNull(session) && Objects.nonNull(session.getId())) {
            redisTemplate.opsForValue().set(getKey(session.getId().toString()), session);
        }
    }

    @Override
    protected Serializable doCreate(Session session) {

        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        saveSession(session);
        if (log.isDebugEnabled()) {
            log.debug("创建session, sessionId = {}", sessionId);
        }
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (log.isDebugEnabled()) {
            log.debug("读取session, sessionId = {}", sessionId.toString());
        }

        if (Objects.isNull(sessionId)) {
            return null;
        }
        return (Session) redisTemplate.opsForValue().get(getKey(sessionId.toString()));
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        if (log.isDebugEnabled()) {
            log.debug("更新session, sessionId = {}", session.getId().toString());
        }
        saveSession(session);
    }

    @Override
    public void delete(Session session) {
        if (log.isDebugEnabled()) {
            log.debug("删除session, sessionId = {}", session.getId().toString());
        }
        redisTemplate.delete(getKey(session.getId().toString()));
    }

    @Override
    public Collection<Session> getActiveSessions() {
        if (log.isDebugEnabled()) {
            log.debug("获取所有活动的session");
        }
        Set<String> sessionValueSet = redisTemplate.keys(PREFIX + "*");
        if (CollectionUtils.isEmpty(sessionValueSet)) {
            return null;
        }
        List<Session> sessionList = redisTemplate.opsForValue().multiGet(sessionValueSet);
        return CollectionUtils.isEmpty(sessionList) ? null : sessionList;
    }

}
