package com.open.demo.shiro.config;

import org.apache.ibatis.javassist.tools.web.Webserver;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

import javax.servlet.ServletRequest;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-11-14-12:11 上午
 */
public class CustomSessionManager extends DefaultWebSessionManager {
    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = getSessionId(sessionKey);

        ServletRequest request = null;
        if (sessionKey instanceof WebSessionKey) {
            request = ((WebSessionKey) sessionKey).getServletRequest();
        }
        if (Objects.nonNull(request) && Objects.nonNull(sessionId)) {
            Session session = (Session) request.getAttribute(sessionId.toString());
            if (Objects.nonNull(session)) {
                return session;
            }
        }
        Session session = super.retrieveSession(sessionKey);
        if (Objects.isNull(session)) {
            return null;
        }
        if (Objects.nonNull(request) && Objects.nonNull(sessionId)) {
            request.setAttribute(sessionId.toString(), session);
        }
        return session;
    }

}
