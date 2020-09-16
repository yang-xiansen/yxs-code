package org.yxs.shiro.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;

/**
 * @Description: jwt工具类
 * @Author: y-xs
 * @Date: 2020/09/16 10:17
 */
public class JwtUtils {

    /**
     * 签发对象：这个用户的id
     * 签发时间：现在
     * 有效时间：30分钟
     * 载荷内容：暂时设计为：这个人的名字，这个人的昵称
     * 加密密钥：这个人的id加上一串字符串
     */
    public static String createToken(String userId, String userName, String sessionId) {

        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 30);
        Date expiresDate = nowTime.getTime();

        return JWT.create().withAudience(userId)   //签发对象
            .withIssuedAt(new Date())    //发行时间
            .withExpiresAt(expiresDate)  //有效时间
            .withClaim("userName", userName)    //载荷，随便写几个都可以
            .withClaim("sessionId", sessionId)
            .sign(Algorithm.HMAC256(userId + "HelloLehr"));   //加密
    }


    /**
     * @Description: 检验合法性，其中secret参数就应该传入的是用户的id
     * @Author: y-xs
     * @Date: 2020/09/16 10:20
     */
    public static void verifyToken(String token, String secret) throws Exception {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret + "HelloLehr")).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            //效验失败
            //这里抛出的异常是我自定义的一个异常，你也可以写成别的
            throw new Exception("校验失败");
        }
    }

    /**
     * 获取签发对象
     */
    public static String getAudience(String token) throws JWTDecodeException {
        String audience = null;
        try {
            audience = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            //这里是token解析失败
            throw new JWTDecodeException("解析失败");
        }
        return audience;
    }


    /**
     * 通过载荷名字获取载荷的值
     */
    public static Claim getClaimByName(String token, String name) {
        return JWT.decode(token).getClaim(name);
    }

}
