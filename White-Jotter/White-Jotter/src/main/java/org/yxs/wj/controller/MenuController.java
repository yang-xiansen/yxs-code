package org.yxs.wj.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yxs.wj.core.Result;
import org.yxs.wj.core.ResultFactory;
import org.yxs.wj.service.MenuService;

/**
 * Menu controller.
 *
 * @author Evan
 * @date 2019/11
 */
@RestController
public class MenuController {
    @Autowired
    MenuService adminMenuService;

    /**
     * @author: yang-xiansen
     * @Date: 2020/08/09 22:10
     * @Description: 获得当前用户有权限的菜单
     */
    @GetMapping("/api/menu")
    public Result menu() {
        return ResultFactory.buildSuccessResult(adminMenuService.getMenusByCurrentUser());
    }

    @GetMapping("/api/admin/role/menu")
    public Result listAllMenus() {
        return ResultFactory.buildSuccessResult(adminMenuService.getMenusByRoleId(1));
    }
}
