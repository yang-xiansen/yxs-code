package org.yxs.oauth.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyUser implements Serializable {
    private static final long serialVersionUID = 3497935890426858541L;

    private String userName;

    private String password;

    /**
     * 是否过期
     */
    private boolean accountNonExpired = true;

    private boolean accountNonLocked= true;

    private boolean credentialsNonExpired= true;

    private boolean enabled= true;
}
