package org.yxs.stomp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * 配置WebSocket
 */
@Configuration
//注解开启使用STOMP协议来传输基于代理(message broker)的消息,这时控制器支持使用@MessageMapping,就像使用@RequestMapping一样
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{
    @Override
    //注册STOMP协议的节点(endpoint),并映射指定的url
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册一个STOMP的endpoint,并指定使用SockJS协议
        registry.addEndpoint("/endpointOyzc").setAllowedOrigins("*").withSockJS();
        //小程序连接 重点在sockJs
        registry.addEndpoint("/endpointOyzc").setAllowedOrigins("*");
    }
    @Override
    //配置消息代理(Message Broker)
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //点对点应配置一个/user消息代理，广播式应配置一个/topic消息代理
        registry.enableSimpleBroker("/topic","/user");
        //点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
        registry.setUserDestinationPrefix("/user/websocket");
    }
}


/**
 * 介绍以上几个相关的注解和方法：
 *
 * 1.@EnableWebSocketMessageBroker：开启使用STOMP协议来传输基于代理(message broker)的消息,这时控制器支持使用@MessageMapping,就像使用@RequestMapping一样。
 *
 * 2.AbstractWebSocketMessageBrokerConfigurer：继承WebSocket消息代理的类，配置相关信息。
 *
 * 3.registry.addEndpoint("/endpointOyzc").setAllowedOrigins("*").withSockJS(); 添加一个访问端点“/endpointGym”,客户端打开双通道时需要的url,允许所有的域名跨域访问，指定使用SockJS协议。
 *
 * 4. registry.enableSimpleBroker("/topic","/user"); 配置一个/topic广播消息代理和“/user”一对一消息代理
 *
 * 5. registry.setUserDestinationPrefix("/user");点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
 *
 */
