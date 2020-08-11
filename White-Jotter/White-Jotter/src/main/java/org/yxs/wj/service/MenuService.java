package org.yxs.wj.service;


import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yxs.wj.dao.MenuDAO;
import org.yxs.wj.domain.entity.Menu;
import org.yxs.wj.domain.entity.RoleMenu;
import org.yxs.wj.domain.entity.UserRole;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MenuService {
    @Autowired
    private MenuDAO menuDAO;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleMenuService roleMenuService;

    public List<Menu> getAllByParentId(int parentId) {
        return menuDAO.findAllByParentId(parentId);
    }

    /**
     * @author: yang-xiansen
     * @Date: 2020/08/09 21:29
     * @Description: 获取当前用户的菜单权限
     */
    public List<Menu> getMenusByCurrentUser() {
        // Get current user in DB.
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.getByName(username);

        // 获取当前用户的角色
        List<Integer> rids = userRoleService.listAllByUid(user.getId())
            .stream().map(UserRole::getRoleId).collect(Collectors.toList());

        // 根据角色获得菜单
        List<Integer> menuIds = roleMenuService.findAllByRoleIds(rids)
            .stream().map(RoleMenu::getMenuId).collect(Collectors.toList());

        //使用jdk8新特性做去重处理  流去重 stream().distinct()
        List<Menu> menus = menuDAO.findAllById(menuIds).stream().distinct().collect(Collectors.toList());

        // Adjust the structure of the menu.
        handleMenus(menus);
        return menus;
    }

    public List<Menu> getMenusByRoleId(int roleId) {
        List<Integer> menuIds = roleMenuService.findAllByRoleId(roleId)
            .stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        List<Menu> menus = menuDAO.findAllById(menuIds);

        handleMenus(menus);
        return menus;
    }


    /**
     * @author: yang-xiansen
     * @Date: 2020/08/09 21:58
     * @Description: 子菜单处理
     */
    public void handleMenus(List<Menu> menus) {
        menus.forEach(m -> {
            List<Menu> children = getAllByParentId(m.getId());
            m.setChildren(children);
        });

        //去除parentId不为0的 removeIf collection方法 用if判断去除
        menus.removeIf(m -> m.getParentId() != 0);
    }


//    public void handleMenus(List<Menu> menus) {
//        for (Menu menu : menus) {
//            List<Menu> children = getAllByParentId(menu.getId());
//            menu.setChildren(children);
//        }
//
//        Iterator<Menu> iterator = menus.iterator();
//        while (iterator.hasNext()) {
//            Menu menu = iterator.next();
//            if (menu.getParentId() != 0) {
//                iterator.remove();
//            }
//        }
//    }

}
