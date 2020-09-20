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
 * @Description: 任务
 * @Author: y-xs
 * @Date: 2020/09/18 15:27
 */
@Component
public class FirstJobDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job firstJob() {
        return jobBuilderFactory.get("firstJob")
            .start(step())
            .build();
    }

    private Step step() {
        return stepBuilderFactory.get("step")
            .tasklet((stepContribution, chunkContext) -> {  //tasklet方法创建step
                System.out.println("执行步骤。。。");
                return RepeatStatus.FINISHED;
            }).build();
    }


}
