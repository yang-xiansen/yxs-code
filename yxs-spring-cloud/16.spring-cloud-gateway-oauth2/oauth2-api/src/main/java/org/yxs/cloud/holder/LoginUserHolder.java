package org.yxs.cloud.holder;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.yxs.cloud.domain.User;

import javax.servlet.http.HttpServletRequest;


/**
* @Description: 获取登录用户信息
* @Author: y-xs
* @Date: 2020/10/12 14:37
*/
@Component
public class LoginUserHolder {

    public User getCurrentUser(){
        //从Header中获取用户信息
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userStr = request.getHeader("user");
        JSONObject userJsonObject = new JSONObject(userStr);
        User user = new User();
        user.setUsername(userJsonObject.getStr("user_name"));
        user.setId(Convert.toLong(userJsonObject.get("id")));
        user.setRoles(Convert.toList(String.class,userJsonObject.get("authorities")));
        return user;
    }
}
