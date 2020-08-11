package org.yxs.wj.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yxs.wj.dao.RoleMenuDAO;
import org.yxs.wj.domain.entity.RoleMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class RoleMenuService {
    @Autowired
    private RoleMenuDAO roleMenuDAO;

    public List<RoleMenu> findAllByRoleId(int roleId) {
        return roleMenuDAO.findAllByRoleId(roleId);
    }

    public List<RoleMenu> findAllByRoleIds(List<Integer> roleIds) {
        return roleMenuDAO.findAllByRoleId(roleIds);
    }

    @Transactional
    public void save(RoleMenu rm) {
        roleMenuDAO.save(rm);
    }

    @Modifying
    @Transactional
    public void updateRoleMenu(int roleId, Map<String, List<Integer>> menusIds) {
        //先删除 再更新
        roleMenuDAO.deleteAllByRoleId(roleId);
        List<RoleMenu> rms = new ArrayList<>();
        for (Integer mid : menusIds.get("menusIds")) {
            RoleMenu rm = new RoleMenu();
            rm.setMenuId(mid);
            rm.setRoleId(roleId);
            rms.add(rm);
        }

        roleMenuDAO.saveAll(rms);
    }
}
