#jwt
> 参考地址：https://www.jianshu.com/p/bca733826e2e

> jwt
  * 什么是jwt?
    * 全称：json web token 是一个非常轻巧的规范 允许我们使用jwt在用户与服务器之间传递安全可靠的信息
    * 组成：jwt实际上就是一个字符串  由头部 载荷  签名 三部分组成
    * 头部：头部用于描述关于该JWT的最基本的信息，例如其类型以及签名所用的算法等。这也可以被表示成一个JSON对象。 --> {"typ":"JWT","alg":"HS256"}
      * 整个头部进行base64编码 --> eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9
    * 载荷（playload）：载荷就是存放有效信息的地方 比如用户信息 整个载荷进行base64编码  -->  xxxxxx
    * 签证（signature）：这个部分需要base64加密后的header和base64加密后的payload使用.连接组成的字符串，然后通过header中声明的加密方式进行加盐secret(参数)组合加密，然后就构成了jwt的第三部分。
      * encodedString = base64UrlEncode(header) + '.' + base64UrlEncode(payload);
      * signature = HMACSHA256(encodedString, 'secret');
    * 将这三部分用.连接成一个完整的字符串,构成了最终的jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9. eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9. TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ


> jwt+oauth2
  * JWTokenConfig配置类 使用JWT替换默认的令牌（默认令牌使用UUID生成）只需要指定TokenStore为JwtTokenStore即可。
  * 在JWT中添加一些额外的信息，比如用户信息，我们需要实现TokenEnhancer（Token增强器）
  * 认证服务器里配置该增强器
  * Java中解析JWT  --> 使用加盐secret参数进行解析

> 测试
  * 获取token请求地址：localhost:8080/oauth/token?username=user1&password=123456&grant_type=password&scope=all
  * 刷新token请求地址：localhost:8080/oauth/token?grant_type=refresh_token&refresh_token=xxxxxxxxxxxxxxxx
  * 刷新地址： 
  * 添加请求头：Authorization --> Basic dGVzdDp0ZXN0  --> key为Authentication，value为Basic加上client_id:client_secret经过base64加密后的值
  * base64 加密地址：http://tool.chinaz.com/Tools/Base64.aspx
