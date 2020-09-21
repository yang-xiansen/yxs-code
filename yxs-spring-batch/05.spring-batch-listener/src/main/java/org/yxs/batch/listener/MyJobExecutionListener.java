package org.yxs.batch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 使用接口方式实现监听器
 * @Author: y-xs
 * @Date: 2020/09/21 8:40
 */
@Component
public class MyJobExecutionListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("before job execute: " + jobExecution.getJobInstance().getJobName());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("after job execute: " + jobExecution.getJobInstance().getJobName());
    }
}
