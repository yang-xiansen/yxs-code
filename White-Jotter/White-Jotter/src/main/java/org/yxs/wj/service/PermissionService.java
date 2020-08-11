package org.yxs.wj.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yxs.wj.dao.PermissionDAO;
import org.yxs.wj.dao.RolePermissionDAO;
import org.yxs.wj.domain.entity.Permission;
import org.yxs.wj.domain.entity.Role;
import org.yxs.wj.domain.entity.RolePermission;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Evan
 * @date 2019/11
 */
@Service
public class PermissionService {
    @Autowired
    private PermissionDAO permissionDAO;
    @Autowired
    private UserRoleService adminUserRoleService;
    @Autowired
    private RoleService adminRoleService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private RolePermissionDAO rolePermissionDAO;
    @Autowired
    private UserService userService;

    public List<Permission> list() {
        return permissionDAO.findAll();
    }

    /**
     * @param requestAPI
     * @Description: 判断用户请求接口的是否在权限表中 如果不在则直接放行了！
     * @return: boolean
     * @Author: yang-xiansen
     * @Date: 2020/08/11 12:53
     */
    public boolean needFilter(String requestAPI) {
        List<Permission> ps = permissionDAO.findAll();
        for (Permission p : ps) {
            // match prefix
            if (requestAPI.startsWith(p.getUrl())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param rid
     * @Description: 根据角色获得权限
     * @return: java.util.List<org.yxs.wj.entity.Permission>
     * @Author: yang-xiansen
     * @Date: 2020/08/11 9:17
     */
    public List<Permission> listPermsByRoleId(int rid) {
        List<Integer> pids = rolePermissionService.findAllByRid(rid)
            .stream().map(RolePermission::getPermissionId).collect(Collectors.toList());
        return permissionDAO.findAllById(pids);
    }

    /**
     * @param username
     * @Description: 根据用户先获得角色 -> 权限 -> urls
     * @return: java.util.Set<java.lang.String>
     * @Author: yang-xiansen
     * @Date: 2020/08/11 9:19
     */
    public Set<String> listPermissionURLsByUser(String username) {
        List<Integer> roleIds = adminRoleService.listRolesByUser(username)
            .stream().map(Role::getId).collect(Collectors.toList());

        List<Integer> permissionIds = rolePermissionDAO.findAllByRoleId(roleIds)
            .stream().map(RolePermission::getPermissionId).collect(Collectors.toList());

        List<Permission> perms = permissionDAO.findAllById(permissionIds);

        Set<String> URLs = perms.stream().map(Permission::getUrl).collect(Collectors.toSet());

        return URLs;
    }
}
