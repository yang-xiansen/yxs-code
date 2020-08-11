package org.yxs.wj.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @author: yang-xiansen
 * @Date: 2020/08/09 17:13
 * @Description: 菜单
 */
@Data
@Entity
@Table(name = "menu")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * 路径
     */
    private String path;

    /**
     * 与 Vue 路由中的 name 属性对应
     */
    private String name;

    /**
     * 中文名
     */
    private String nameZh;

    /**
     * 图标
     */
    private String iconCls;

    /**
     * 组件名，用于解析路由对应的组件
     */
    private String component;

    /**
     * 父类菜单
     */
    private int parentId;


    /**
     * 用于存储子节点
     */
    //数据库中不存在对应字段的属性，需要用 @Transient 注记标注出来
    @Transient
    private List<Menu> children;
}



