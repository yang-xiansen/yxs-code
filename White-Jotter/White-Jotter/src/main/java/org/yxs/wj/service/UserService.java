package org.yxs.wj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yxs.wj.dao.UserDao;
import org.yxs.wj.entity.User;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;


    public boolean isExist(String username) {
        User user = getByName(username);
        return null!=user;
    }

    public User getByName(String username) {
        return userDao.findByUsername(username);
    }

    public User get(String username, String password){
        return userDao.getByUsernameAndPassword(username, password);
    }

    public void add(User user) {
        userDao.save(user);
    }

}
