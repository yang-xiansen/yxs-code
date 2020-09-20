package org.yxs.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @Description: 任务嵌套 即将多个任务Job转换为特殊的Step
 * @Author: y-xs
 * @Date: 2020/09/18 17:56
 */
@Component
public class NestedJobDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    // 父任务
    @Bean
    public Job parentJob() {
        return jobBuilderFactory.get("parentJob")
            .start(childJobOneStep())
            .next(childJobTwoStep())
            .build();
    }


    // 将任务转换为特殊的步骤
    private Step childJobOneStep() {
        return new JobStepBuilder(new StepBuilder("childJobOneStep"))
            .job(childJobOne())
            .launcher(jobLauncher)  //任务执行器
            .repository(jobRepository)  //任务仓库
            .transactionManager(platformTransactionManager)  //事务管理器
            .build();
    }

    // 将任务转换为特殊的步骤
    private Step childJobTwoStep() {
        return new JobStepBuilder(new StepBuilder("childJobTwoStep"))
            .job(childJobTwo())
            .launcher(jobLauncher)  //任务执行器
            .repository(jobRepository)  //任务仓库
            .transactionManager(platformTransactionManager)  //事务管理器
            .build();
    }

    // 子任务一
    private Job childJobOne() {
        return jobBuilderFactory.get("childJobOne")
            .start(
                stepBuilderFactory.get("childJobOneStep")
                    .tasklet((stepContribution, chunkContext) -> {
                        System.out.println("子任务一执行步骤。。。");
                        return RepeatStatus.FINISHED;
                    }).build()
            ).build();
    }

    // 子任务二
    private Job childJobTwo() {
        return jobBuilderFactory.get("childJobTwo")
            .start(
                stepBuilderFactory.get("childJobTwoStep")
                    .tasklet((stepContribution, chunkContext) -> {
                        System.out.println("子任务二执行步骤。。。");
                        return RepeatStatus.FINISHED;
                    }).build()
            ).build();
    }

}
