package org.yxs.wj.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yxs.wj.dao.UserRoleDAO;
import org.yxs.wj.domain.entity.Role;
import org.yxs.wj.domain.entity.UserRole;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserRoleService {
    @Autowired
    private UserRoleDAO userRoleDAO;

    public List<UserRole> listAllByUid(int userId) {
        return userRoleDAO.findAllByUserId(userId);
    }

    //    @Modifying
    @Transactional
    public void saveRoleChanges(int userId, List<Role> roles) {
        //先删除 再增加
        userRoleDAO.deleteAllByUserId(userId);
        List<UserRole> urs = new ArrayList<>();
        roles.forEach(r -> {
            UserRole ur = new UserRole();
            ur.setUserId(userId);
            ur.setRoleId(r.getId());
            urs.add(ur);
        });
        userRoleDAO.saveAll(urs);
    }
}
