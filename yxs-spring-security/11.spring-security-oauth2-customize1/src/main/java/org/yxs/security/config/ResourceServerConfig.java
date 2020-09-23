package org.yxs.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.yxs.security.filter.SmsCodeFilter;
import org.yxs.security.handler.MyAuthenticationFailureHandler;
import org.yxs.security.handler.MyAuthenticationSuccessHandler;
import org.yxs.security.sms.SmsAuthenticationConfig;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler successHandler;

    @Autowired
    private MyAuthenticationFailureHandler failureHandler;

    @Autowired
    private SmsCodeFilter smsCodeFilter;

    @Autowired
    private SmsAuthenticationConfig smsAuthenticationConfig;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)  //添加自定义的验证码过滤器
            .formLogin()
            .loginProcessingUrl("/login")  //处理表单
            .successHandler(successHandler)
            .failureHandler(failureHandler)
            .and()
            .authorizeRequests() //授权配置
            .antMatchers("/code/sms").permitAll()  //放权
            .anyRequest()  //所有请求
            .authenticated()  //都需要认证
            .and()
            .csrf().disable()
            .apply(smsAuthenticationConfig); // 将短信验证码认证配置加到 Spring Security 中;


    }
}
