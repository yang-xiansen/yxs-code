package org.yxs.shiro.listener;


import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: shiro中的session监听
 * @Author: y-xs
 * @Date: 2020/09/15 16:38
 */
public class ShiroSessionListener implements SessionListener {

    private final AtomicInteger sessionCount = new AtomicInteger();

    @Override
    public void onStart(Session session) {
        sessionCount.incrementAndGet();
    }

    @Override
    public void onStop(Session session) {
        sessionCount.decrementAndGet();
    }

    @Override
    public void onExpiration(Session session) {
        sessionCount.decrementAndGet();
    }
}
