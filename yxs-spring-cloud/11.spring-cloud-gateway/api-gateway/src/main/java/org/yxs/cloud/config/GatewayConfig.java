package org.yxs.cloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
* @Description: java bean配置路由
* @Author: y-xs
* @Date: 2020/10/11 15:43
*/
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("path_route2", r -> r.path("/user/getByUsername")
                .uri("http://localhost:8201/user/getByUsername"))
            .build();
    }
}
