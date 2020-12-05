package org.yxs.design.impl;


import org.yxs.design.CacheService;
import org.yxs.design.RedisUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author: y-xs
 * @date: 2020/12/04 13:24
 * @description: 单机操作redis
 */
public class CacheServiceImpl implements CacheService {

    private RedisUtils redisUtils = new RedisUtils();

    public String get(String key) {
        return redisUtils.get(key);
    }

    public void set(String key, String value) {
        redisUtils.set(key, value);
    }

    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        redisUtils.set(key, value, timeout, timeUnit);
    }

    public void del(String key) {
        redisUtils.del(key);
    }

}
