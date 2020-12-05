package org.yxs.design;

import java.util.concurrent.TimeUnit;

/**
 * @author: y-xs
 * @date: 2020/12/04 11:21
 * @description: if else 切换 操作redis
 */
public interface CacheService {

    String get(final String key, int redisType);

    void set(String key, String value, int redisType);

    void set(String key, String value, long timeout, TimeUnit timeUnit, int redisType);

    void del(String key, int redisType);

}
