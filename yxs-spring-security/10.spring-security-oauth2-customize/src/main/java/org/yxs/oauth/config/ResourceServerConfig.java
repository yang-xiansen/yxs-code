package org.yxs.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.yxs.oauth.handle.MyAuthenticationFailureHandler;
import org.yxs.oauth.handle.MyAuthenticationSuccessHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler successHandler;

    @Autowired
    private MyAuthenticationFailureHandler failureHandler;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
            .loginProcessingUrl("/login")  //处理表单
            .successHandler(successHandler)
            .failureHandler(failureHandler)
            .and()
            .authorizeRequests() //授权配置
            .anyRequest()  //所有请求
            .authenticated()  //都需要认证
            .and()
            .csrf().disable();


    }
}
