package com.open.demo.shiro.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-11-13-3:52 下午
 */
@Configuration
public class ShiroConfig {

    @Bean
    public SecurityManager securityManager(Realm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(realm);
        securityManager.setSessionManager(sessionManager());
        SecurityUtils.setSecurityManager(securityManager);

        return securityManager;
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        return sessionManager;
    }

    @Bean
    public Realm customRealm() {
        return new CustomRealm();
    }
}
