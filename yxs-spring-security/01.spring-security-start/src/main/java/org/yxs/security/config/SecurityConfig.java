package org.yxs.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic()  //开启basic验证
        http.formLogin()//开启表单认证
            .and()
            .authorizeRequests() //授权配置
            .anyRequest()  //所有请求
            .authenticated();  //都需要认证
    }
}
