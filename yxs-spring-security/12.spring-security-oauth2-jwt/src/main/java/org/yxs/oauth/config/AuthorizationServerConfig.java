package org.yxs.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 认证服务器
 * @Author: yang-xiansen
 * @Date: 2020/06/03 17:18
 */
@Configuration
@EnableAuthorizationServer  //开启认证服务器
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailService;

    @Resource(name = "tokenStore")
    private TokenStore tokenStore;   //存储 jwt token

    @Autowired
    private TokenEnhancer tokenEnhancer;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;  //jwt token 转换器

    // 认证的端点
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {

        //配置jwt增强器
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancers = new ArrayList();
        enhancers.add(tokenEnhancer);
        enhancers.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(enhancers);

        // jwt的配置
        endpoints.tokenStore(tokenStore).
            accessTokenConverter(jwtAccessTokenConverter).
            tokenEnhancer(enhancerChain)

            //交给oauth2管理
            .authenticationManager(authenticationManager)
            .userDetailsService(userDetailService);
    }

    // 配置认证的客户端（给谁发令牌）--> client 存储数据库
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
            .withClient("test1")  //client_id
            .secret(new BCryptPasswordEncoder().encode("test1111")) //client_secret
            .accessTokenValiditySeconds(3600) //有效时间为3600s  在此时间内 token一直没变
            .refreshTokenValiditySeconds(864000) //使用refresh_token 刷新获得token 有效时间
            .scopes("all", "a", "b", "c")    //scope只能指定为all，a，b或c中的某个值 否则将获取失败；
            .authorizedGrantTypes("password", "refresh_token")   //客户端test1验证使用密码模式

            .and()

            .withClient("test2")   //客户端test2验证没有限制
            .secret(new BCryptPasswordEncoder().encode("test2222")) //client_secret
            .accessTokenValiditySeconds(7200);
    }
}
