package org.yxs.auto.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yxs.auto.annotation.EnableHelloWorld;

/**
* @Description: 自动装配  参考SpringFactoriesLoader
* @Author: yang-xiansen
* @Date: 2020/09/07 10:42
*/
@Configuration
@EnableHelloWorld
//@ConditionalOnProperty(name = "helloworld", havingValue = "true")
public class HelloWorldAutoConfiguration {

//    @Bean
//    public String stringBean(){
//        return "world,hello";
//    }
}
