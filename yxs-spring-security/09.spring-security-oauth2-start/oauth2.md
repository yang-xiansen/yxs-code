#OAuth2

> 授权码模式
  * 在继承的WebSecurityConfigurerAdapter配置中加个 @EnableAuthorizationServer 注解 代表着就是开启授权码模式
  * 获得code地址：http://localhost:8080/oauth/authorize?response_type=code&client_id=test&redirect_uri=https://www.baidu.com&scope=all&state=hello
  * 获得token：http://localhost:8080/oauth/token?grant_type=authorization_code&code=ue15Pg&client_id=test&redirect_uri=https://www.baidu.com&scope=all
    * 添加请求头：Authorization --> Basic dGVzdDp0ZXN0  --> key为Authentication，value为Basic加上client_id:client_secret经过base64加密后的值
    * base64 加密地址：http://tool.chinaz.com/Tools/Base64.aspx
  * 参考文章地址：https://www.jianshu.com/p/a2a94e5d6d35


> 密码模式
  * localhost:8080/oauth/token?username=user1&password=123456&grant_type=password&scope=all&client_id=test&client_secret=test 
    * 添加请求头：Authorization --> Basic dGVzdDp0ZXN0  --> key为Authentication，value为Basic加上client_id:client_secret经过base64加密后的值
    * base64 加密地址：http://tool.chinaz.com/Tools/Base64.aspx