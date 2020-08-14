package org.yxs.wj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yxs.wj.domain.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User getByUsernameAndPassword(String username, String password);

    //自定义sql语句查询
//    @Query(value = "select new User(u.id,u.username,u.name,u.phone,u.email,u.enabled) from User u")
//    List<User> list();


}
