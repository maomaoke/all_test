package com.open.demo.shiro.config;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-11-14-5:34 下午
 */
@SuppressWarnings(value = "unchecked")
public class ShiroRedisCacheManager implements CacheManager {

    private ShiroRedisCache<String, Object> cache;

    public ShiroRedisCacheManager(ShiroRedisCache<String, Object> cache) {
        this.cache = cache;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return (Cache<K, V>) cache;
    }
}
