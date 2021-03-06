package com.basic.zyz.config;

import com.basic.zyz.common.constant.RedisEnum;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class ShiroRedisCache<K,V> implements Cache<K,V>{

    @Autowired
    private RedisTemplate<K,V> redisTemplate;

    @Override
    public V get(K k) throws CacheException {
        return redisTemplate.opsForValue().get(k);
    }

    @Override
    public V put(K k, V v) throws CacheException {
        System.out.println("K：" + k);
        System.out.println("V：" + v);
        redisTemplate.opsForValue().set(k,v, RedisEnum.REDIS_USER.getTime(), TimeUnit.SECONDS);
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        V v = redisTemplate.opsForValue().get(k);
        redisTemplate.opsForValue().getOperations().delete(k);
        return v;
    }

    @Override
    public void clear() throws CacheException {
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
