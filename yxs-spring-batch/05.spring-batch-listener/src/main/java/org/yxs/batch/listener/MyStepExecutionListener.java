package org.yxs.batch.listener;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.stereotype.Component;

/**
 * @Description: 使用注解方式实现监听器
 * @Author: y-xs
 * @Date: 2020/09/21 8:40
 */
@Component
public class MyStepExecutionListener {

    @BeforeStep
    public void breforeStep(StepExecution stepExecution) {
        System.out.println("before step execute: " + stepExecution.getStepName());
    }

    @AfterStep
    public void afterStep(StepExecution stepExecution) {
        System.out.println("after step execute: " + stepExecution.getStepName());
    }
}
