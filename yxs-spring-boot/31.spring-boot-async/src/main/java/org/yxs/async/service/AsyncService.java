package org.yxs.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AsyncService {

    @Async("asyncThreadPoolTaskExecutor")
    public void asyncMethod() {
        sleep();
        log.info("异步方法内部线程名称：{}", Thread.currentThread().getName());
    }

    @Async("asyncThreadPoolTaskExecutor")
    public Future<String> asyncMethod1() {
        sleep();
        log.info("异步方法内部线程名称：{}", Thread.currentThread().getName());
        return new AsyncResult<>("hello async");
    }

    public void syncMethod() {
        log.info("同步方法内部线程名称：{}", Thread.currentThread().getName());
        sleep();
    }

    private void sleep() {
        try {
            log.info("睡眠方法内部线程名称：{}", Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
