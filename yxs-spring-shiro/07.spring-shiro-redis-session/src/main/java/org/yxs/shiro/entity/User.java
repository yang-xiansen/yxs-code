package org.yxs.shiro.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = -5440372534300871944L;

    private Integer id;
    private String userName;
    private String password;
    private Date createTime;
    private String status;
}
