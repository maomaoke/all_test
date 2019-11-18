package com.open.demo.shiro.config;

import com.open.demo.shiro.event.LoginSuccessEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    private static final String CACHE_KEY = "kick_out_cache";

    private final Cache<String, Deque<Serializable>> cache;

    private SessionManager sessionManager;


    private int maxSession;
    private boolean kickOutAfter;
    private String kickOutUrl;

    public KickOutSessionControlFilter(SessionManager sessionManager, ShiroSessionProperties sessionProperties,
                                       CacheManager cacheManager) {
        this.sessionManager = sessionManager;
        this.maxSession = sessionProperties.getMaxSession();
        this.kickOutAfter = sessionProperties.getKickOutAfter();
        this.kickOutUrl = sessionProperties.getKickOutUrl();
        this.cache = cacheManager.getCache(CACHE_KEY);
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

        Deque<Serializable> sessionIdDeque = cache.get(getCacheKey(username));

        if (CollectionUtils.isEmpty(sessionIdDeque)) {
            sessionIdDeque = new LinkedList<>();
        }

        //如果队列里没有此sessionId，且用户没有被踢出；放入队列
        if (!sessionIdDeque.contains(sessionId) && session.getAttribute(KICK_OUT_KEY) == null) {
            synchronized (cache) {
                sessionIdDeque.push(sessionId);
            }
        }



        synchronized (cache) {
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

        cache.put(getCacheKey(username), sessionIdDeque);

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
            return true;
        }

        return true;
    }



    private String getCacheKey(String key) {
        return KICK_OUT_KEY + ":" + key;
    }

    public synchronized void appendUser(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {

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
