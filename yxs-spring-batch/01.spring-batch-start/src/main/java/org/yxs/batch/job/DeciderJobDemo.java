package org.yxs.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.yxs.batch.decider.MyDecider;

/**
 * @Description: 使用自定义决策器
 * @Author: y-xs
 * @Date: 2020/09/18 17:47
 */
@Component
public class DeciderJobDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private MyDecider myDecider;

    @Bean
    public Job deciderJob() {
        return jobBuilderFactory.get("deciderJob1")
            .start(step1())
            .next(myDecider)  // 指定自定义决策器
            .from(myDecider).on("weekend").to(step2())  // 如果决策器返回weekend，那么执行step2，如果决策器返回workingDay，那么执行step3。如果执行了step3，那么无论step3的结果是什么，都将执行step4。
            .from(myDecider).on("workingDay").to(step3())
            .from(step3()).on("*").to(step4())
            .end()
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


    private Step step4() {
        return stepBuilderFactory.get("step4")
            .tasklet((stepContribution, chunkContext) -> {
                System.out.println("执行步骤四操作。。。");
                return RepeatStatus.FINISHED;
            }).build();

    }

}
