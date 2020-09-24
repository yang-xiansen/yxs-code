package org.yxs.oauth.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
* @Description: 配置jwt增强 即配置一些额外的信息 比如用户信息
* @Author: yang-xiansen
* @Date: 2020/06/02 17:57
*/
public class JWTTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Authentication authentication = oAuth2Authentication.getUserAuthentication();
//        if (Strings.isEmpty(authentication)){
//            principal = (UserDetails) oAuth2Authentication.getDetails();
//        } else {
//            principal = (UserDetails) authentication.getPrincipal();
//        }
        Map<String, Object> info = new HashMap();
        info.put("message", "hello world");
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);
        return oAuth2AccessToken;
    }
}
