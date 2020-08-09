package org.yxs.wj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yxs.wj.entity.Menu;

import java.util.List;


public interface MenuDAO extends JpaRepository<Menu, Integer> {

    /**
     * @param id
     * @Description: 根据id查找菜单
     * @return: org.yxs.wj.entity.Menu
     * @Author: yang-xiansen
     * @Date: 2020/08/09 20:51
     */
    public Menu findById(int id);

    /**
     * @param parentId
     * @Description: 根据父类id查找子菜单
     * @return: java.util.List<org.yxs.wj.entity.Menu>
     * @Author: yang-xiansen
     * @Date: 2020/08/09 20:52
     */
    public List<Menu> findAllByParentId(int parentId);
}

