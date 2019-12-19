package com.open.demo.shiro.config;

import com.open.demo.shiro.event.LoginSuccessEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-11-15-2:02 下午
 */
@Slf4j
public class KickOutSessionControlFilter extends AccessControlFilter implements ApplicationListener<LoginSuccessEvent> {

    private static final String KICK_OUT_KEY = "kick_out";

    private RedisTemplate<String, Deque<Serializable>> redisTemplate;

    private SessionManager sessionManager;

    private final int maxSession;
    private final boolean kickOutAfter;
    private final String kickOutUrl;

    public KickOutSessionControlFilter(SessionManager sessionManager, ShiroSessionProperties sessionProperties,
                                       RedisTemplate<String, Deque<Serializable>> redisTemplate) {
        this.sessionManager = sessionManager;
        this.maxSession = sessionProperties.getMaxSession();
        this.kickOutAfter = sessionProperties.getKickOutAfter();
        this.kickOutUrl = sessionProperties.getKickOutUrl();
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated()) {
            return true;
        }

        Session session = subject.getSession();
        String username = (String) subject.getPrincipal();
        Serializable sessionId = session.getId();

        Deque<Serializable> sessionIdDeque = redisTemplate.opsForValue().get(getCacheKey(username));

        if (CollectionUtils.isEmpty(sessionIdDeque)) {
            sessionIdDeque = new LinkedList<>();
        }

        //如果队列里没有此sessionId，且用户没有被踢出；放入队列
        if (!sessionIdDeque.contains(sessionId) && session.getAttribute(KICK_OUT_KEY) == null) {
            synchronized (this) {
                sessionIdDeque.push(sessionId);
            }
        }

        synchronized (this) {
            while (sessionIdDeque.size() > maxSession) {
                Serializable kickoutSessionId = null;
                //如果踢出后者
                if (kickOutAfter) {
                    kickoutSessionId = sessionIdDeque.removeFirst();
                } else { //否则踢出前者
                    kickoutSessionId = sessionIdDeque.removeLast();
                }
                try {
                    Session kickOutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
                    if (kickOutSession != null) {
                        //设置会话的kickout属性表示踢出了
                        kickOutSession.setAttribute(KICK_OUT_KEY, true);
                    }
                } catch (Exception e) {//ignore exception
                }
            }
        }

//        redisTemplate.opsForValue().set(getCacheKey(username), sessionIdDeque, Duration.ofSeconds(this.expire));
        redisTemplate.opsForValue().set(getCacheKey(username), sessionIdDeque);
        session.setAttribute("username", username);

        //如果被踢出了，直接退出，重定向到踢出后的地址
        if (session.getAttribute(KICK_OUT_KEY) != null) {
            //会话被踢出了
            try {
                subject.logout();
            } catch (Exception e) { //ignore
            }
            saveRequest(request);
            if (!StringUtils.isEmpty(kickOutUrl)) {
                WebUtils.issueRedirect(request, response, kickOutUrl);
            }
            return false;
        }

        return true;
    }



    private String getCacheKey(String key) {
        return KICK_OUT_KEY + ":" + key;
    }


    @Override
    public void onApplicationEvent(LoginSuccessEvent event) {
        try {
            onAccessDenied(event.getRequest(), event.getResponse());
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("网络故障", e);
            }
        }
    }
}
