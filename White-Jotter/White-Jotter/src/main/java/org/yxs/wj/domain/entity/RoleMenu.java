package org.yxs.wj.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: yang-xiansen
 * @Date: 2020/08/09 20:50
 * @Description: 菜单与角色关联
 */
@Data
@Entity
@Table(name = "role_menu")
@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class RoleMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * 角色id
     */
    private int roleId;

    /**
     * 菜单id
     */
    private int menuId;
}
