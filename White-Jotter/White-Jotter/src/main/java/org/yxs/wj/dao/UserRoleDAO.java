package org.yxs.wj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yxs.wj.entity.UserRole;

import java.util.List;


public interface UserRoleDAO extends JpaRepository<UserRole, Integer> {

    /**
     * @param userId
     * @Description: 根据用户id查找角色
     * @return: java.util.List<org.yxs.wj.entity.UserRole>
     * @Author: yang-xiansen
     * @Date: 2020/08/09 20:52
     */
    List<UserRole> findAllByUserId(int userId);

    /**
     * @param userId
     * @Description: 根据用户id删除角色
     * @return: void
     * @Author: yang-xiansen
     * @Date: 2020/08/09 20:52
     */
    void deleteAllByUserId(int userId);
}
