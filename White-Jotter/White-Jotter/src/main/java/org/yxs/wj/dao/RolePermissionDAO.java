package org.yxs.wj.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.yxs.wj.domain.entity.RolePermission;

import java.util.List;

/**
 * @Description: 角色权限dao
 * @Author: yang-xiansen
 * @Date: 2020/08/11 9:11
 */
public interface RolePermissionDAO extends JpaRepository<RolePermission, Integer> {
    List<RolePermission> findAllByRoleId(int roleId);

    List<RolePermission> findAllByRoleId(List<Integer> roleIds);

    void deleteAllByRoleId(int roleId);
}
