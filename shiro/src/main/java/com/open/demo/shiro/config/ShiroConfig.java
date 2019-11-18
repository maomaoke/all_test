package com.open.demo.shiro.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
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
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-11-13-3:52 下午
 */
@EnableConfigurationProperties(ShiroProperties.class)
@Configuration
public class ShiroConfig {

    @Autowired
    private RedisTemplate<String, Object> shiroRedisTemplate;

    @Autowired
    private ShiroProperties shiroProperties;

    @Autowired
    private KickOutSessionControlFilter kickOutSessionControlFilter;

    @Bean
    public RedisTemplate<String, Object> shiroRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
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

        Map<String, String> anonMap = Maps.newHashMap();

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
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    @Bean
    public SessionManager sessionManager(SessionDAO sessionDAO) {
        DefaultWebSessionManager sessionManager = new CustomSessionManager();
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setCacheManager(cacheManager());
        sessionManager.setSessionIdCookie(new SimpleCookie(shiroProperties.getSession().getCookieName()));
        return sessionManager;
    }

    @Bean("redisSessionDAO")
    public SessionDAO redisSessionDAO() {
        return new RedisSessionDAO(shiroRedisTemplate, shiroProperties.getSession());
    }

    @Bean
    public CacheManager cacheManager() {
        return new ShiroRedisCacheManager(shiroRedisCache());
    }

    @Bean
    public ShiroRedisCache<String, Object> shiroRedisCache() {
        return new ShiroRedisCache<>(shiroRedisTemplate, shiroProperties.getCache());
    }

    @Bean
    public Realm customRealm() {
        return new CustomRealm();
    }

    @Bean
    public KickOutSessionControlFilter kickOutSessionControlFilter(SessionManager sessionManager,
                                                                   ShiroProperties shiroProperties, CacheManager cacheManager) {
        return new KickOutSessionControlFilter(sessionManager, shiroProperties.getSession(), cacheManager);
    }
}
