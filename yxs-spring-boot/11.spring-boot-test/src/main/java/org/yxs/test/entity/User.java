package org.yxs.test.entity;

import lombok.Data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String passwd;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "status")
    private String status;
}