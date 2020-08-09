package org.yxs.wj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yxs.wj.entity.Role;


public interface RoleDAO extends JpaRepository<Role, Integer> {

    /**
     * @author: yang-xiansen
     * @Date: 2020/08/09 20:49
     * @Description: 根据id查找角色
     */
    Role findById(int id);
}
