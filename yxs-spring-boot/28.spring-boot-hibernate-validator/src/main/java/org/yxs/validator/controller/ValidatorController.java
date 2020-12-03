package org.yxs.validator.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yxs.validator.entity.User;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@RestController
@Validated
public class ValidatorController {

    @GetMapping("get")
    public String get(
//        @NotBlank(message = "名字不能为空") String name,
//        @Email(message = "格式不合法") String email
        @NotBlank(message = "{required}") String name,
        @Email(message = "{invalid}") String email
    ) {
        return "success";
    }


    @GetMapping("get1")
    public String get1(
        @Valid User user
    ) {
        return "success";
    }
}
