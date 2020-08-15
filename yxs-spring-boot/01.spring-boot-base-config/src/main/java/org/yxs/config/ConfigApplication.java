package org.yxs.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.yxs.config.propertiey.BlogProperties1;

@SpringBootApplication
@EnableConfigurationProperties({BlogProperties1.class})
public class ConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
//        SpringApplication application = new SpringApplication(ConfigApplication.class);

//        关闭banner
//        application.setBannerMode(Banner.Mode.OFF);

        //禁止项目的配置被命令行修改
//        application.setAddCommandLineProperties(false);
//        application.run(args);

    }
}
