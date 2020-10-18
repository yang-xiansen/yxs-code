package org.yxs.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yxs.cloud.domain.User;
import org.yxs.cloud.holder.LoginUserHolder;


/**
 * @Description: 用户信息接口
 * @Author: y-xs
 * @Date: 2020/10/12 14:39
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private LoginUserHolder loginUserHolder;

    @GetMapping("/currentUser")
    public User currentUser() {
        return loginUserHolder.getCurrentUser();
    }

}
