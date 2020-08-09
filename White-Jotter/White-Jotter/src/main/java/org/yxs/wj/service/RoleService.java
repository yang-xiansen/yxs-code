package org.yxs.wj.service;

;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.yxs.wj.dao.RoleDAO;
import org.yxs.wj.entity.Role;
import org.yxs.wj.entity.UserRole;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Evan
 * @date 2019/11
 */
@Service
public class RoleService {
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private MenuService menuService;

    public List<Role> listWithPermsAndMenus() {
        List<Role> roles = roleDAO.findAll();
//        List<AdminPermission> perms;
//        List<AdminMenu> menus;
//        for (AdminRole role : roles) {
//            perms = adminPermissionService.listPermsByRoleId(role.getId());
//            menus = adminMenuService.getMenusByRoleId(role.getId());
//            role.setPerms(perms);
//            role.setMenus(menus);
//        }
        return roles;
    }

    public List<Role> findAll() {
        return roleDAO.findAll();
    }


    public void addOrUpdate(Role role) {
        roleDAO.save(role);
    }

    public List<Role> listRolesByUser(String username) {
        int uid = userService.getByName(username).getId();
        List<Integer> rids = userRoleService.listAllByUid(uid)
            .stream().map(UserRole::getRoleId).collect(Collectors.toList());
        return roleDAO.findAllById(rids);
    }

    public Role updateRoleStatus(Role role) {
        Role roleInDB = roleDAO.findById(role.getId());
        roleInDB.setEnabled(role.isEnabled());
        return roleDAO.save(roleInDB);
    }

    public void editRole(@RequestBody Role role) {
        roleDAO.save(role);
//        adminRolePermissionService.savePermChanges(role.getId(), role.getPerms());
    }
}
