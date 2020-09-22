package org.yxs.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.yxs.security.filter.SmsCodeFilter;
import org.yxs.security.handler.MyAuthenticationFailureHandler;
import org.yxs.security.sms.SmsAuthenticationConfig;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private SmsCodeFilter smsCodeFilter;

    @Autowired
    private SmsAuthenticationConfig smsAuthenticationConfig;

    @Autowired
    private MySessionExpiredStrategy sessionExpiredStrategy;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)  //添加自定义的验证码过滤器
            .formLogin() // 表单登录
//            .loginPage("/login.html")   //指定跳转到登录页面的请求url
            .loginPage("/authentication/require") // 登录跳转 URL
            .loginProcessingUrl("/login")  //表单的action="/login"
            .successHandler(authenticationSuccessHandler)
            .failureHandler(authenticationFailureHandler)
            .and()
            .authorizeRequests() // 授权配置
            .antMatchers("/authentication/require", "/login.html", "/code/sms", "/css/**", "/session/invalid").permitAll()  //放权
            .anyRequest()  // 所有请求
            .authenticated() // 都需要认证
            .and().csrf().disable()
            .apply(smsAuthenticationConfig)  // 将短信验证码认证配置加到 Spring Security 中
            .and()
            .sessionManagement() // 添加 Session管理器
            .invalidSessionUrl("/session/invalid") // Session失效后跳转到这个链接
            .maximumSessions(1)  // session最大在线
            .expiredSessionStrategy(sessionExpiredStrategy);  //session并发失效处理策略
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
