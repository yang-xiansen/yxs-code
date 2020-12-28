package org.yxs.design.adapter;

import java.util.concurrent.TimeUnit;

/**
 * @author: y-xs
 * @date: 2020/12/04 13:24
 * @description: 适配器接口
 */
public interface ICacheAdapter {

    String get(String key);

    void set(String key, String value);

    void set(String key, String value, long timeout, TimeUnit timeUnit);

    void del(String key);

}
