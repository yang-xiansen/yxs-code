package org.yxs.validator.entity;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @Description: 对象字段校验
 * @Author: yang-xiansen
 * @Date: 2020/09/09 9:19
 */
@Data
public class User {

    @NotBlank(message = "{required}")
    private String name;


    @Email(message = "{invalid}")
    private String email;

}
