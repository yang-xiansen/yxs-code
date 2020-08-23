package org.yxs.fi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.yxs.fi.filter.TimeFilter1;
import org.yxs.fi.interceptor.TimeInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yang-xiansen
 * @Date: 2020/08/23 14:40
 * @Description: 配置类
 * 过滤器先于拦截器执行，晚于拦截器结束。
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * @author: yang-xiansen
     * @Date: 2020/08/23 11:45
     * @Description: 注入过滤器
     */
    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        TimeFilter1 timeFilter1 = new TimeFilter1();
        filterRegistrationBean.setFilter(timeFilter1);

        List<String> urlList = new ArrayList<>();
        urlList.add("/*");

        filterRegistrationBean.setUrlPatterns(urlList);
        return filterRegistrationBean;
    }

    @Autowired
    private TimeInterceptor timeInterceptor;


    /**
     * @author: yang-xiansen
     * @Date: 2020/08/23 11:46
     * @Description: 注入拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }

}
