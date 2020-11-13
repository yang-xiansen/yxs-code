package org.yxs.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;


@Configuration
public class AsyncPoolConfig {

    /**
     * @Description: 自定义线程池
     * @Author: yang-xiansen
     * @Date: 2020/09/09 15:02
     */
    @Bean
    public ThreadPoolTaskExecutor asyncThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);    //线程池核心线程得数量
        executor.setMaxPoolSize(200);   //线程池维护得线程最大数量
        executor.setQueueCapacity(25);  //缓冲队列
        executor.setKeepAliveSeconds(200);  //超出核心线程数外的线程在空闲时候的最大存活时间
        executor.setThreadNamePrefix("asyncThread");  //线程名前缀
        executor.setWaitForTasksToCompleteOnShutdown(true);  //是否等待所有线程执行完毕才关闭线程池
        executor.setAwaitTerminationSeconds(60);  //waitForTasksToCompleteOnShutdown的等待的时长，默认值为0，即不等待。

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());  //当没有线程可以使用时的处理策略
        executor.initialize();
        return executor;
    }





}
