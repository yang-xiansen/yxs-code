package org.yxs.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @Description: 资源服务器
 * @Author: yang-xiansen
 * @Date: 2020/06/03 17:18
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.formLogin()
            .and()
            .authorizeRequests()
            .antMatchers("/actuator/**", "/token/**", "/oauth/**", "/index").permitAll()
            .anyRequest().authenticated().and()//配置访问权限控制，必须认证过后才可以访问
            .csrf().disable();
    }

}
