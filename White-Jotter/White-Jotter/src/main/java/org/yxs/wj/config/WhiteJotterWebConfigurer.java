package org.yxs.wj.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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
}
