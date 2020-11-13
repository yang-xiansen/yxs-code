package org.yxs.auto.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfiguration {

    @Bean
    public String hello() {
        return "hello world i am come on";
    }
}
