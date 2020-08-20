package org.yxs.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
* @Description: swagger2配置
 * 访问地址：http://localhost:8080/swagger-ui.html
* @Author: yang-xiansen
* @Date: 2020/08/19 10:37
*/
@Configuration
@EnableSwagger2 //启用swagger2
public class SwaggerConfig {
    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(buildApiInf())
            .select()
            .apis(RequestHandlerSelectors.basePackage("org.yxs.swagger.controller")) //扫描包路径
            .paths(PathSelectors.any())
            .build();
    }
    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
            .title("系统RESTful API文档")
            .contact(new Contact("yxs", "https://yang-xiansen.top", "666666@qq.com"))
            .version("1.0")
            .build();
    }
}
