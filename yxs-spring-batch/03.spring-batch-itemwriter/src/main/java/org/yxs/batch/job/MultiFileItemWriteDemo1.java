package org.yxs.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.yxs.batch.entity.TestData;

import java.util.Arrays;

/**
 * @Description: 多文本输出
 * @Author: y-xs
 * @Date: 2020/09/20 15:52
 */
@Component
public class MultiFileItemWriteDemo1 {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private ListItemReader<TestData> simpleReader;
    @Autowired
    private ItemStreamWriter<TestData> fileItemWriter;
    @Autowired
    private ItemStreamWriter<TestData> xmlFileItemWriter;

    @Bean
    public Job multiFileItemWriterJob2() {
        return jobBuilderFactory.get("multiFileItemWriterJob2")
            .start(step())
            .build();
    }

    private Step step() {
        return stepBuilderFactory.get("step")
            .<TestData, TestData>chunk(2)
            .reader(simpleReader)
            .writer(multiFileItemWriter())
            .build();
    }

    // 输出数据到多个文件
    private CompositeItemWriter<TestData> multiFileItemWriter() {
        // 使用CompositeItemWriter代理
        CompositeItemWriter<TestData> writer = new CompositeItemWriter<>();
        // 设置具体写代理
        writer.setDelegates(Arrays.asList(fileItemWriter, xmlFileItemWriter));
        return writer;
    }
}
