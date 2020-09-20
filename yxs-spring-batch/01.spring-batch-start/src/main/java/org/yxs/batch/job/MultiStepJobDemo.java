package org.yxs.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Description: 多步骤任务
 * @Author: y-xs
 * @Date: 2020/09/18 16:07
 */
@Component
public class MultiStepJobDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job multiStepJob() {
        return jobBuilderFactory.get("multiStepJob")
            .start(step1())
            .next(step2())
            .next(step3())
            .build();
    }

    private Step step1() {
        return stepBuilderFactory.get("step1")
            .tasklet((stepContribution, chunkContext) -> {
                System.out.println("执行步骤一操作。。。");
                return RepeatStatus.FINISHED;
            }).build();
    }

    private Step step2() {
        return stepBuilderFactory.get("step2")
            .tasklet((stepContribution, chunkContext) -> {
                System.out.println("执行步骤二操作。。。");
                return RepeatStatus.FINISHED;
            }).build();
    }

    private Step step3() {
        return stepBuilderFactory.get("step3")
            .tasklet((stepContribution, chunkContext) -> {
                System.out.println("执行步骤三操作。。。");
                return RepeatStatus.FINISHED;
            }).build();
    }

}
