package org.yxs.shiro.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Role implements Serializable {

    private static final long serialVersionUID = -227437593919820521L;
    private Integer id;
    private String name;
    private String description;


}
