package org.yxs.shiro.service;

import org.yxs.shiro.entity.UserOnline;

import java.util.List;

public interface SessionService {

    /**
     * @Description: 获得在线用户
     * @Author: y-xs
     * @Date: 2020/09/15 16:52
     */
    List<UserOnline> list();

    /**
     * @Description: 强退用户
     * @Author: y-xs
     * @Date: 2020/09/15 16:52
     */
    boolean forceLogout(String sessionId);
}
