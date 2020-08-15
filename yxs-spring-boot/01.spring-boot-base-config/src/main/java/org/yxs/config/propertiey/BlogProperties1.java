package org.yxs.config.propertiey;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: yang-xiansen
 * @Date: 2020/08/15 17:31
 * @Description: 通过@ConfigurationProperties加载配置 @EnableConfigurationProperties({ConfigBean.class})来启用该配置
 */

@ConfigurationProperties(prefix = "yxs.blog")
@Data
public class BlogProperties1 {

    private String name;

    private String title;

    private String wholeTitle;


}
