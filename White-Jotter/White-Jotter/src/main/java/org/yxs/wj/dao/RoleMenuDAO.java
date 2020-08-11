package org.yxs.wj.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.yxs.wj.domain.entity.RoleMenu;

import java.util.List;


public interface RoleMenuDAO extends JpaRepository<RoleMenu, Integer> {

    /**
     * @param roelId
     * @Description: 根据角色id 查找
     * @return: java.util.List<org.yxs.wj.entity.RoleMenu>
     * @Author: yang-xiansen
     * @Date: 2020/08/09 20:53
     */
    List<RoleMenu> findAllByRoleId(int roelId);

    /**
     * @param roleIds
     * @Description: 根据角色ids 查找
     * @return: java.util.List<org.yxs.wj.entity.RoleMenu>
     * @Author: yang-xiansen
     * @Date: 2020/08/09 20:53
     */
    List<RoleMenu> findAllByRoleId(List<Integer> roleIds);

    /**
     * @param roleId
     * @Description: 根据角色id删除
     * @return: void
     * @Author: yang-xiansen
     * @Date: 2020/08/09 20:53
     */
    void deleteAllByRoleId(int roleId);
}
