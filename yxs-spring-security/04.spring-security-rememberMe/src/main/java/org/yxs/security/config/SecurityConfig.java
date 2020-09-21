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
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.yxs.security.filter.ValidateCodeFilter;
import org.yxs.security.handler.MyAuthenticationFailureHandler;
import org.yxs.security.service.UserDetailService;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)  //添加自定义的验证码过滤器
            .formLogin() // 表单登录
//            .loginPage("/login.html")   //指定跳转到登录页面的请求url
            .loginPage("/authentication/require") // 登录跳转 URL
            .loginProcessingUrl("/login")  //表单的action="/login"
            .successHandler(authenticationSuccessHandler) //处理登陆成功
            .failureHandler(authenticationFailureHandler) //处理登陆失败
            .and()
            .rememberMe()
            .tokenRepository(persistentTokenRepository()) //配置token持久化仓库
            .tokenValiditySeconds(3600)  //remember过期时间 单位：s
            .userDetailsService(userDetailService)  //处理自动登陆逻辑
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

    /**
     * @Description: 记住我 配置token持久化对象
     * @Author: y-xs
     * @Date: 2020/09/21 16:29
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }

}
