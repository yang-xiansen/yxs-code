package org.yxs.wj.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yxs.wj.dao.RolePermissionDAO;
import org.yxs.wj.domain.entity.Permission;
import org.yxs.wj.domain.entity.RolePermission;

import java.util.ArrayList;
import java.util.List;

/**
* @Description: 角色与权限关联service
* @Author: yang-xiansen
* @Date: 2020/08/11 9:23
*/
@Service
public class RolePermissionService {
    @Autowired
    private RolePermissionDAO rolePermissionDAO;

    List<RolePermission> findAllByRid(int roleId) {
        return rolePermissionDAO.findAllByRoleId(roleId);
    }

//    @Modifying
    @Transactional
    public void savePermChanges(int roleId, List<Permission> perms) {
        rolePermissionDAO.deleteAllByRoleId(roleId);
        List<RolePermission> rps = new ArrayList<>();
        perms.forEach(p -> {
            RolePermission rp = new RolePermission();
            rp.setRoleId(roleId);
            rp.setPermissionId(p.getId());
            rps.add(rp);
        });
        rolePermissionDAO.saveAll(rps);
    }
}
