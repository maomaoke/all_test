package com.open.demo.shiro.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-11-14-5:14 下午
 */
@Slf4j
@SuppressWarnings(value = "unchecked")
public class ShiroRedisCache<K, V> implements Cache<K, V> {

    private RedisTemplate<K, V> redisTemplate;

    private ShiroCacheProperties cacheProperties;

    @SuppressWarnings("rawtypes")
    public ShiroRedisCache(RedisTemplate<K, V> redisTemplate, ShiroCacheProperties cacheProperties) {
        this.redisTemplate = redisTemplate;
        this.cacheProperties = cacheProperties;
    }

    private K getCacheKey(Object k) {
        return (K) (cacheProperties.getPrefix() + ":" + k);
    }


    @Override
    public V get(K k) throws CacheException {
        redisTemplate.boundValueOps(getCacheKey(k)).expire(cacheProperties.getExpire(), TimeUnit.SECONDS);
        return redisTemplate.opsForValue().get(getCacheKey(k));
    }

    @Override
    public V put(K k, V v) throws CacheException {
        V result = redisTemplate.opsForValue().getAndSet(getCacheKey(k), v);
        redisTemplate.expire(getCacheKey(k), cacheProperties.getExpire(), TimeUnit.SECONDS);
        return result;
    }

    @Override
    public V remove(K k) throws CacheException {
        V session = redisTemplate.opsForValue().get(getCacheKey(k));
        redisTemplate.delete(k);
        return session;
    }

    @Override
    public void clear() throws CacheException {
        redisTemplate.delete(getCacheKey("*"));
    }

    @Override
    public int size() {
        return keys().size();
    }

    @Override
    public Set<K> keys() {
        return redisTemplate.keys(getCacheKey("*"));
    }

    @Override
    public Collection<V> values() {
        Set<K> keys = keys();
        return redisTemplate.opsForValue().multiGet(keys);
    }
}
