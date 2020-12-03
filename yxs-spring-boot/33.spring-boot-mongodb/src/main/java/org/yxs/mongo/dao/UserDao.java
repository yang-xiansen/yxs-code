package org.yxs.mongo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.yxs.mongo.entity.User;

import java.util.List;

@Repository
public interface UserDao extends MongoRepository<User, String> {

    /**
     * @Description: 年龄区间查询
     * @Author: yang-xiansen
     * @Date: 2020/09/09 16:37
     */
    List<User> findByAgeBetween(Integer from, Integer to);

    /**
     * @Description: 自定义多条件查询 类似jpa
     * @Author: yang-xiansen
     * @Date: 2020/09/09 17:02
     */
    List<User> findByAgeBetweenAndNameEqualsAndDescriptionIsLike(Integer from, Integer to, String name, String description);

}
