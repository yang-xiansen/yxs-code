package org.yxs.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.yxs.security.filter.ValidateCodeFilter;
import org.yxs.security.handler.MyAuthenticationFailureHandler;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)  //权限控制 配合授权注解使用
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)  //添加自定义的验证码过滤器
            .formLogin() // 表单登录
//            .loginPage("/login.html")   //指定跳转到登录页面的请求url
            .loginPage("/authentication/require") // 登录跳转 URL
            .loginProcessingUrl("/login")  //表单的action="/login"
            .successHandler(authenticationSuccessHandler)
            .failureHandler(authenticationFailureHandler)
            .and()
            .authorizeRequests() // 授权配置
            .antMatchers("/authentication/require", "/login.html", "/code/image", "/css/**").permitAll()  //放权
            .anyRequest()  // 所有请求
            .authenticated() // 都需要认证
            .and().csrf().disable();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
