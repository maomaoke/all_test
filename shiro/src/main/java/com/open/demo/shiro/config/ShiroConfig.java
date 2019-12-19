package com.open.demo.shiro.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-11-13-3:52 下午
 */
@EnableConfigurationProperties(ShiroProperties.class)
@Configuration
public class ShiroConfig {

    private static final long DEFAULT_TIMEOUT = 1800;

    @Autowired
    private RedisTemplate<String, Session> shiroRedisTemplate;

    @Autowired
    private RedisTemplate<String, Deque<Serializable>> kickOutRedisTemplate;

    @Autowired
    private ShiroProperties shiroProperties;

    @Autowired
    private KickOutSessionControlFilter kickOutSessionControlFilter;


    @Bean
    public RedisTemplate<String, Session> shiroRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Session> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setDefaultSerializer(new ShiroRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer(StandardCharsets.UTF_8));
        return redisTemplate;
    }

    @Bean
    public RedisTemplate<String, Deque<Serializable>> kickOutRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Deque<Serializable>> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setDefaultSerializer(new ShiroRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer(StandardCharsets.UTF_8));
        return redisTemplate;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filters = Maps.newHashMap();
        filters.put("authFilter", new UrlAuthorizationFilter());
        filters.put("kickOutFilter", kickOutSessionControlFilter);
        shiroFilterFactoryBean.setFilters(filters);

        shiroFilterFactoryBean.setLoginUrl("/unLogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuthorized");

        Map<String, String> anonMap = Maps.newLinkedHashMap();

        List<String> anonymityUrl = anonymityUrl(shiroProperties.getAnonymityUrl());
        anonymityUrl.forEach(url -> anonMap.put(url, "anon"));

        anonMap.put("/**", "kickOutFilter, authFilter");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(anonMap);

        return shiroFilterFactoryBean;
    }

    private List<String> anonymityUrl(String originString) {
        if (StringUtils.isEmpty(originString)) {
            return Lists.newArrayListWithCapacity(0);
        }
        String[] urls = originString.replace(" ", "").trim().split(",");
        return Lists.newArrayList(urls);
    }

    @Bean
    public SecurityManager securityManager(Realm realm, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(realm);
        securityManager.setSessionManager(sessionManager);
        securityManager.setCacheManager(shiroCacheManager());
        return securityManager;
    }

    @Bean
    public DefaultWebSessionManager sessionManager(SessionDAO sessionDAO) {
        DefaultWebSessionManager sessionManager = new CustomSessionManager();
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setCacheManager(shiroCacheManager());
        sessionManager.setSessionIdCookie(shiroCache());
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setDeleteInvalidSessions(true);

        Long expire = shiroProperties.getSession().getExpire();
        if (Objects.isNull(expire) || expire < DEFAULT_TIMEOUT) {
            expire = DEFAULT_TIMEOUT * 1000;
        } else {
            expire *= 1000;
        }
        sessionManager.setGlobalSessionTimeout(10000);
        sessionManager.setSessionListeners(Lists.newArrayList(new DefaultSessionListener(kickOutRedisTemplate)));

        sessionManager.setSessionValidationInterval(5000);
        sessionManager.setSessionValidationSchedulerEnabled(true);

        ExecutorServiceSessionValidationScheduler sessionValidationScheduler = sessionValidationScheduler();

        sessionManager.setSessionValidationScheduler(sessionValidationScheduler);
        sessionValidationScheduler.setSessionManager(sessionManager);

        return sessionManager;
    }

    @Bean
    public ExecutorServiceSessionValidationScheduler sessionValidationScheduler() {
        ExecutorServiceSessionValidationScheduler executorServiceSessionValidationScheduler = new ExecutorServiceSessionValidationScheduler();
        executorServiceSessionValidationScheduler.setInterval(5000);
        return executorServiceSessionValidationScheduler;
    }

    @Bean("redisSessionDAO")
    public SessionDAO redisSessionDAO() {
        return new RedisSessionDAO(shiroRedisTemplate, shiroProperties.getSession());
    }

    @Bean
    public CacheManager shiroCacheManager() {
        return new ShiroRedisCacheManager(shiroRedisCache());
    }

    public Cookie shiroCache() {
        SimpleCookie cookie = new SimpleCookie(shiroProperties.getSession().getCookieName());
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(-1);
        return cookie;
    }


    @Bean
    public ShiroRedisCache<String, Session> shiroRedisCache() {
        return new ShiroRedisCache<>(shiroRedisTemplate, shiroProperties.getCache());
    }

    @Bean
    public Realm customRealm() {
        return new CustomRealm();
    }

    @Bean
    public KickOutSessionControlFilter kickOutSessionControlFilter(SessionManager sessionManager,
                                                                   ShiroProperties shiroProperties) {
        return new KickOutSessionControlFilter(sessionManager, shiroProperties.getSession(), kickOutRedisTemplate);
    }
}
