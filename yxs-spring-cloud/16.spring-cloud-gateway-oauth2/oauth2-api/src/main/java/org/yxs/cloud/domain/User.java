package org.yxs.cloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private Integer status;
    private List<String> roles;

    public User(){}
}
