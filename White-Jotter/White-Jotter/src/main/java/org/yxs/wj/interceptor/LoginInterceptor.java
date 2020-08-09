package org.yxs.wj.interceptor;

import io.netty.handler.codec.http.HttpMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.yxs.wj.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description: 登陆拦截器
 * @Author: yang-xiansen
 * @Date: 2020/08/07 8:33
 */
public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        // 放行 options 请求，否则无法让前端带上自定义的 header 信息，导致 sessionID 改变，shiro 验证失败
        if (HttpMethod.OPTIONS.toString().equals(httpServletRequest.getMethod())) {
            httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }

        Subject subject = SecurityUtils.getSubject();
        // 使用 shiro 验证
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            return false;
        }
        return true;
    }


//    @Override
//    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        HttpSession session = httpServletRequest.getSession();
//        String contextPath = session.getServletContext().getContextPath();
//
//        //需要拦截的路径
//        String[] requireAuthPages = new String[]{
//            "index"
////            "/"
//        };
//
//        String uri = httpServletRequest.getRequestURI();
//
//        uri = StringUtils.remove(uri, contextPath + "/");
//        String page = uri;
//
//        if (begingWith(page, requireAuthPages)) {
//            User user = (User) session.getAttribute("user");
//            if (user == null) {
//                httpServletResponse.sendRedirect("login");
//                return false;
//            }
//        }
//        return true;
//    }

    /**
     * @param page
     * @param requiredAuthPages
     * @Description: 判断是不是需要拦截的uri
     * @return: boolean
     * @Author: yang-xiansen
     * @Date: 2020/08/07 8:35
     */
    private boolean begingWith(String page, String[] requiredAuthPages) {
        boolean result = false;
        for (String requiredAuthPage : requiredAuthPages) {
            if (StringUtils.startsWith(page, requiredAuthPage)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
