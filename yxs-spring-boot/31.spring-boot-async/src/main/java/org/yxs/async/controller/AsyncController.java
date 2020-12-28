package org.yxs.async.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yxs.async.service.AsyncService;

import java.util.concurrent.Future;

@RestController
@Slf4j
public class AsyncController {


    @Autowired
    private AsyncService asyncService;

    @GetMapping("async")
    public void testAsync() {
        long start = System.currentTimeMillis();
        log.info("异步方法开始");

        asyncService.asyncMethod();

        log.info("异步方法结束");
        long end = System.currentTimeMillis();
        log.info("总耗时：{} ms", end - start);
    }

    @SneakyThrows
    @GetMapping("async1")
    public void testAsync1() {
        long start = System.currentTimeMillis();
        log.info("异步方法开始");

        Future<String> stringFuture = asyncService.asyncMethod1();
        String result = stringFuture.get();   //会阻塞
        log.info("异步方法返回值：{}", result);

        log.info("异步方法结束");
        long end = System.currentTimeMillis();
        log.info("总耗时：{} ms", end - start);
    }

    @GetMapping("sync")
    public void testSync() {
        long start = System.currentTimeMillis();
        log.info("同步方法开始");

        asyncService.syncMethod();

        log.info("同步方法结束");
        long end = System.currentTimeMillis();
        log.info("总耗时：{} ms", end - start);
    }


}
