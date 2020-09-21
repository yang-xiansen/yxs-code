package org.yxs.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * @Description: 任务重启机制
 * @Author: y-xs
 * @Date: 2020/09/21 9:53
 */
@Component
public class RestartJobDemo {


    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job restartJob() {
        return jobBuilderFactory.get("restartJob")
            .start(step())
            .build();
    }

    private Step step() {

        return stepBuilderFactory.get("step")
            .<String, String>chunk(2)
            .reader(listItemReader())
            .writer(list -> list.forEach(System.out::println))
            .allowStartIfComplete(true)  //项目重新启动都将执行这个Step
            .startLimit(2)  //设置Step重启执行的次数
            .build();
    }

    private ListItemReader<String> listItemReader() {
        ArrayList<String> datas = new ArrayList<>();
        IntStream.range(0, 5).forEach(i -> datas.add(String.valueOf(i)));
        return new ListItemReader<>(datas);
    }

}
