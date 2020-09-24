package org.yxs.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Description: 认证服务器
 * @Author: yang-xiansen
 * @Date: 2020/06/03 17:18
 */
@Configuration
@EnableAuthorizationServer  //开启认证服务器
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailService;

    //存储 jwt token
    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    //jwt token 转换器
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey("test_key");
        return accessTokenConverter;
    }

    // 认证的端点
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {


        // jwt的配置
        endpoints.tokenStore(jwtTokenStore()).
            accessTokenConverter(jwtAccessTokenConverter())
            //交给oauth2管理
            .userDetailsService(userDetailService);
    }

    // 配置认证的客户端（给谁发令牌）--> client 存储数据库
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
            .withClient("test1")  //client_id
            .secret(new BCryptPasswordEncoder().encode("test1")) //client_secret
            .accessTokenValiditySeconds(3600) //有效时间为3600s  在此时间内 token一直没变
            .refreshTokenValiditySeconds(864000) //使用refresh_token 刷新获得token 有效时间
            .scopes("all", "a", "b", "c")    //scope只能指定为all，a，b或c中的某个值 否则将获取失败；
            .authorizedGrantTypes("password", "refresh_token", "authorization_code")   //客户端test1验证使用密码模式
            .redirectUris("http://127.0.0.1:9090/app1/login")
            .autoApprove(true)
            .and()

            .withClient("test2")   //客户端test2验证没有限制
            .secret(new BCryptPasswordEncoder().encode("test2")) //client_secret
            .accessTokenValiditySeconds(7200)
            .scopes("all", "a", "b", "c")    //scope只能指定为all，a，b或c中的某个值 否则将获取失败；
            .authorizedGrantTypes("password", "refresh_token", "authorization_code")   //客户端test1验证使用密码模式
            .autoApprove(true)
            .redirectUris("http://127.0.0.1:9091/app2/login");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("isAuthenticated()"); // 获取密钥需要身份认证
    }
}
