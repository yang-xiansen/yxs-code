package org.yxs.auto.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Description: 自动装配
 * @Author: yang-xiansen
 * @Date: 2020/09/07 10:34
 */
@EnableAutoConfiguration
public class EnableAutoConfigurationBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(EnableAutoConfigurationBootstrap.class)
            .web(WebApplicationType.NONE)
            .run(args);
        String hello = context.getBean("hello", String.class);
        System.out.println("hello Bean：" + hello);
        context.close();
    }
}
