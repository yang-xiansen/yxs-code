package org.yxs.design.adapter;


import org.yxs.design.EGM;

import java.util.concurrent.TimeUnit;

/**
 * @author: y-xs
 * @date: 2020/12/04 13:25
 * @description: 集群B适配器
 */
public class EGMCacheAdapter implements ICacheAdapter {

    private EGM egm = new EGM();

    public String get(String key) {
        return egm.gain(key);
    }

    public void set(String key, String value) {
        egm.set(key, value);
    }

    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        egm.setEx(key, value, timeout, timeUnit);
    }

    public void del(String key) {
        egm.delete(key);
    }
}
