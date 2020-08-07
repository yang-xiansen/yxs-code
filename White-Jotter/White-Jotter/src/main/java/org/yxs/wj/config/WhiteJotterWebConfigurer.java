package org.yxs.wj.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.yxs.wj.interceptor.LoginInterceptor;

@SpringBootConfiguration
public class WhiteJotterWebConfigurer implements WebMvcConfigurer {

    /**
     * @param
     * @Description:将bean交给spring
     * @return: org.yxs.wj.interceptor.LoginInterceptor
     * @Author: yang-xiansen
     * @Date: 2020/08/07 8:40
     */
    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    /**
     * @param registry
     * @Description: 添加拦截器
     * @return: void
     * @Author: yang-xiansen
     * @Date: 2020/08/07 8:41
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //除了不拦截index.html 拦截所有
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html");
    }

    /**
     * @param registry
     * @Description: 跨域处理 也可以使用@CrossOrigin注解来解决跨域问题
     * @return: void
     * @Author: yang-xiansen
     * @Date: 2020/08/07 21:42
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //所有请求都允许跨域
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("*")
            .allowedHeaders("*");
    }

    /**
     * @param registry
     * @Description: 将url访问路径 与 本地存放文件的地址对应起来
     * @return: void
     * @Author: yang-xiansen
     * @Date: 2020/08/07 22:03
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/file/**").addResourceLocations("file:" + "d:/workspace/img/");
    }

}
