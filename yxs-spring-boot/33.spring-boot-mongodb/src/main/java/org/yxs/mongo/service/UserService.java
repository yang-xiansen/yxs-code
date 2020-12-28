package org.yxs.mongo.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.yxs.mongo.dao.UserDao;
import org.yxs.mongo.entity.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MongoTemplate template;

    /**
     * @Description: 获得全部用户
     * @Author: yang-xiansen
     * @Date: 2020/09/09 16:29
     */
    public List<User> getUsers() {
        return userDao.findAll();
    }

    /**
     * @Description: 获得用户
     * @Author: yang-xiansen
     * @Date: 2020/09/09 16:29
     */
    public Optional<User> getUser(String id) {
        return this.userDao.findById(id);
    }

    /**
     * 新增和修改都是 save方法，
     * id 存在为修改，id 不存在为新增
     */
    public User createUser(User user) {
        user.setId(null);
        return userDao.save(user);
    }

    /**
     * @Description: 删除用户
     * @Author: yang-xiansen
     * @Date: 2020/09/09 16:29
     */
    public void deleteUser(String id) {
        this.userDao.findById(id)
            .ifPresent(user -> this.userDao.delete(user));
    }

    /**
     * @Description: 更新用户
     * @Author: yang-xiansen
     * @Date: 2020/09/09 16:29
     */
    public void updateUser(String id, User user) {
        this.userDao.findById(id)
            .ifPresent(
                u -> {
                    u.setName(user.getName());
                    u.setAge(user.getAge());
                    u.setDescription(user.getDescription());
                    this.userDao.save(u);
                }
            );
    }


    /**
     * @Description: 年龄区间查询
     * @Author: yang-xiansen
     * @Date: 2020/09/09 16:37
     */
    List<User> findByAgeBetween(Integer from, Integer to) {
        return userDao.findByAgeBetween(from, to);
    }


    /**
     * @Description: 分页查询
     * @Author: yang-xiansen
     * @Date: 2020/09/09 17:07
     */
    public Page<User> getUserByCondition(int size, int page, User user) {

        Query query = new Query();
        Criteria criteria = new Criteria();

        if (!StringUtils.isEmpty(user.getName())) {
            criteria.and("name").is(user.getName());
        }
        if (!StringUtils.isEmpty(user.getDescription())) {
            criteria.and("description").regex(user.getDescription());
        }

        query.addCriteria(criteria);
        Sort sort = new Sort(Sort.Direction.DESC, "age");
        Pageable pageable = PageRequest.of(page, size, sort);

        List<User> users = template.find(query.with(pageable), User.class);
        return PageableExecutionUtils.getPage(users, pageable, () -> template.count(query, User.class));
    }

}
