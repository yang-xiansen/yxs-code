package org.yxs.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @Description: 创建认证服务器很简单，只需要在Spring Security的配置类上使用@EnableAuthorizationServer注解标注即可。创建AuthorizationServerConfig
 * @Author: yang-xiansen
 * @Date: 2020/06/02 9:40
 */

@Configuration
@EnableAuthorizationServer   //使用@EnableAuthorizationServer注解 标志认证服务器
public class AuthorizationServerConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/oauth/authorize/**").permitAll();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
