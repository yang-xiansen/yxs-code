package org.yxs.wj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yxs.wj.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User getByUsernameAndPassword(String username, String password);

}
