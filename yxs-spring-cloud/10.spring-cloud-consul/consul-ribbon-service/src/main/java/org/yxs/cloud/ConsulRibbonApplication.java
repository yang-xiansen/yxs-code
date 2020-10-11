package org.yxs.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsulRibbonApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsulRibbonApplication.class, args);
    }
}
