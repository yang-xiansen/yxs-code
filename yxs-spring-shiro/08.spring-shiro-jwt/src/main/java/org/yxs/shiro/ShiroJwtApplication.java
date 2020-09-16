package org.yxs.shiro;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.yxs.shiro.entity.SystemProperties;

@SpringBootApplication
@EnableConfigurationProperties(SystemProperties.class)
public class ShiroJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroJwtApplication.class, args);
    }
}
