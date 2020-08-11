package org.yxs.wj.domain.dto;


import lombok.Data;
import lombok.ToString;
import org.yxs.wj.domain.dto.base.OutputConverter;
import org.yxs.wj.domain.entity.Role;
import org.yxs.wj.domain.entity.User;

import java.util.List;

/**
 * @author Evan
 * @date 2020/4/1 19:57
 */
@Data
@ToString
public class UserDTO implements OutputConverter<UserDTO, User> {

    private int id;

    private String username;

    private String name;

    private String phone;

    private String email;

    private boolean enabled;

    private List<Role> roles;

}
