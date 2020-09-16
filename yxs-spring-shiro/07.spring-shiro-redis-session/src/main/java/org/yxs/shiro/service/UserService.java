package org.yxs.shiro.service;


import org.yxs.shiro.entity.User;

public interface UserService {

    User findByUserName(String userName);

}
