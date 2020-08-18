package org.yxs.thymeleaf.entity;


import lombok.Data;

@Data
public class Account {
    private String account;
    private String name;
    private String password;
    private String accountType;
    private String tel;

    public Account(String account, String name, String password, String accountType, String tel) {
        super();
        this.account = account;
        this.name = name;
        this.password = password;
        this.accountType = accountType;
        this.tel = tel;
    }
}
