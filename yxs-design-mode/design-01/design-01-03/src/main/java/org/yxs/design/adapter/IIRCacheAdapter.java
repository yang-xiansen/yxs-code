package org.yxs.design.adapter;

import org.yxs.design.IIR;

import java.util.concurrent.TimeUnit;

/**
 * @author: y-xs
 * @date: 2020/12/04 13:25
 * @description: 集群A适配器
 */
public class IIRCacheAdapter implements ICacheAdapter {

    private IIR iir = new IIR();

    public String get(String key) {
        return iir.get(key);
    }

    public void set(String key, String value) {
        iir.set(key, value);
    }

    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        iir.setExpire(key, value, timeout, timeUnit);
    }

    public void del(String key) {
        iir.del(key);
    }

}
