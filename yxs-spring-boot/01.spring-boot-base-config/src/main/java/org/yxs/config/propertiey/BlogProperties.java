package org.yxs.config.propertiey;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: yang-xiansen
 * @Date: 2020/08/15 17:31
 * @Description: 通过@Value("${属性名}")来加载配置文件中的属性值
 */

@Component
@Data
public class BlogProperties {

    @Value("${yxs.blog.name}")
    private String name;

    @Value("${yxs.blog.title}")
    private String title;

    @Value("${yxs.blog.wholeTitle}")
    private String wholeTitle;


}
