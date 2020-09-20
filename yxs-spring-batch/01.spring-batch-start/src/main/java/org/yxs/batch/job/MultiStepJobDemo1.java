package org.yxs.batch.job;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Description: 多步骤任务 根据上个任务的状态判断是否执行下一个步骤
 * @Author: y-xs
 * @Date: 2020/09/18 16:07
 */
@Component
public class MultiStepJobDemo1 {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job multiStepJob1() {
        return jobBuilderFactory.get("multiStepJob2")
            .start(step1())
            .on(ExitStatus.COMPLETED.getExitCode()).to(step2())
            .from(step2())
            .on(ExitStatus.COMPLETED.getExitCode()).to(step3())
            .from(step3()).end()
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
