package org.yxs.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.yxs.batch.entity.TestData;

import javax.sql.DataSource;

/**
 * @Description: 多文本输出
 * @Author: y-xs
 * @Date: 2020/09/20 15:52
 */
@Component
public class MultiFileItemWriteDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private ListItemReader<TestData> simpleReader;
    @Autowired
    private ItemStreamWriter<TestData> fileItemWriter;
    @Autowired
    private ItemStreamWriter<TestData> xmlFileItemWriter;

    @Bean
    public Job multiFileItemWriterJob() {
        return jobBuilderFactory.get("multiFileItemWriterJob")
            .start(step())
            .build();
    }

    private Step step() {
        return stepBuilderFactory.get("step")
            .<TestData, TestData>chunk(2)
            .reader(simpleReader)
            .writer(classifierMultiFileItemWriter())
            .stream(fileItemWriter)
            .stream(xmlFileItemWriter)
            .build();
    }

    // 将数据分类，然后分别输出到对应的文件(此时需要将writer注册到ioc容器，否则报
    // WriterNotOpenException: Writer must be open before it can be written to)
    private ClassifierCompositeItemWriter<TestData> classifierMultiFileItemWriter() {
        ClassifierCompositeItemWriter<TestData> writer = new ClassifierCompositeItemWriter<>();
        writer.setClassifier((Classifier<TestData, ItemWriter<? super TestData>>) testData -> {
            try {
                // id能被2整除则输出到普通文本，否则输出到xml文本
                return testData.getId() % 2 == 0 ? fileItemWriter : xmlFileItemWriter;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
        return writer;
    }
}
