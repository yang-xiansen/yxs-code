package org.yxs.auto.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.yxs.auto.service.TestService;

@ComponentScan("org.yxs.auto.service")
public class ServiceBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ServiceBootstrap.class)
            .web(WebApplicationType.NONE).run(args);
        TestService testService = context.getBean("testService", TestService.class);
        System.out.println("TestService Bean：" + testService);
        context.close();
    }

}
