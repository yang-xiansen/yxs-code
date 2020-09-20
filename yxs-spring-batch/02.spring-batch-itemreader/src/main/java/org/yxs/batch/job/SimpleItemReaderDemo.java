package org.yxs.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.yxs.batch.reader.SimpleItemReader;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 简单数据读取
 * @Author: y-xs
 * @Date: 2020/09/19 10:44
 */
@Component
public class SimpleItemReaderDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job simpleItemReaderJob() {
        return jobBuilderFactory.get("simpleItemReaderJob")
            .start(step())
            .build();
    }

    private Step step() {
        return stepBuilderFactory.get("step")
            .<String, String>chunk(2)   //chunk方法创建step
            .reader(simpleItemReader())
            .writer(list -> list.forEach(System.out::println))
            .build();
    }

    private ItemReader<String> simpleItemReader() {
        List<String> data = Arrays.asList("java", "c++", "javascript", "python");
        return new SimpleItemReader(data);
    }


}
















