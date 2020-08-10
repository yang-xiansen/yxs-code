package org.yxs.wj.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
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
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**")
            .excludePathPatterns("/index.html")
            .excludePathPatterns("/api/login")
            .excludePathPatterns("/api/register")
            .excludePathPatterns("/api/logout");
    }

    /**
     * @param registry
     * @Description: 跨域处理 也可以使用@CrossOrigin注解来解决跨域问题  此方法配置之后再使用自定义拦截器时跨域相关配置就会失效。
     * @return: void
     * @Author: yang-xiansen
     * @Date: 2020/08/07 21:42
     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        //所有请求都允许跨域
//        registry.addMapping("/**")
//            .allowCredentials(true)  //允许跨域使用cookie
//            .allowedOrigins("*")  //跨域的主机+端口 为了安全 建议不使用*
//            .allowedMethods("*") //跨域的方法
//            .allowedHeaders("*"); //跨域的请求头
//    }


    /**
     * @param
     * @Description: 解决使用 自定义拦截器解决跨域问题 参考文章：https://blog.csdn.net/weixin_33958585/article/details/88678712?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
     * @return: org.springframework.web.cors.CorsConfiguration
     * @Author: yang-xiansen
     * @Date: 2020/08/09 11:57
     */
    private CorsConfiguration corsConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //请求常用的三种配置，*代表允许所有，当时你也可以自定义属性（比如header只能带什么，只能是post方式等等）
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig());
        return new CorsFilter(source);
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
        registry.addResourceHandler("/api/file/**").addResourceLocations("file:" + "f:/workspace/img/");
    }

}
