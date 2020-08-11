package org.yxs.wj.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.yxs.wj.domain.entity.Permission;

/**
 * @Description: 权限dao
 * @Author: yang-xiansen
 * @Date: 2020/08/11 9:10
 */
public interface PermissionDAO extends JpaRepository<Permission, Integer> {
    Permission findById(int id);
}
