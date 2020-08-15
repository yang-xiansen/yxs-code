package org.yxs.config.propertiey;

import lombok.Data;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author: yang-xiansen
 * @Date: 2020/08/15 22:25
 * @Description: 自定义配置文件以及加载  注解@PropertySource("classpath:yxs.properties")指明了使用哪个配置文件。
 * 使用注解@EnableConfigurationProperties({YxsPropertis.class})来启用该配置(2.0以后不需要了)
 */
@Data
@Configurable
@Component
@ConfigurationProperties(prefix = "yxs")
@PropertySource("classpath:yxs.properties")
public class YxsProperties {

    private String name;

    private String age;


}
