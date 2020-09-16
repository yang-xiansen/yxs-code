package org.yxs.shiro.config;

import lombok.SneakyThrows;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.yxs.shiro.util.JwtUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.UUID;

/**
 * @Description: 继承DefaultWebSessionManager 重写获取sessionId得方法
 * @Author: y-xs
 * @Date: 2020/09/16 13:31
 */
public class CustomSessionManager extends DefaultWebSessionManager {


    //这里我为了省事用了lombok的标签
    @SneakyThrows
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {

        String token = WebUtils.toHttp(request).getHeader("token");
        System.out.println("会话管理器得到的token是：" + token);
        if (token == null || token.length() < 1) {
            return UUID.randomUUID().toString();
        }

        //在这里验证一下jwt了，虽然我知道这样不好
        String userId = JwtUtils.getAudience(token);
        JwtUtils.verifyToken(token, userId);
        String sessionId = JwtUtils.getClaimByName(token, "sessionId").asString();

        if (sessionId == null) {
            return new Exception("获取sessionId失败");
        }


        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "header");
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
        request.setAttribute(ShiroHttpServletRequest.SESSION_ID_URL_REWRITING_ENABLED, isSessionIdUrlRewritingEnabled());

        return sessionId;
    }


}
