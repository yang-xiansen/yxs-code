package org.yxs.batch.job;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.yxs.batch.entity.TestData;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Description: 输出json文本数据
 * @Author: y-xs
 * @Date: 2020/09/19 16:07
 */
@Component
public class JsonFileItemWriterDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private ListItemReader<TestData> simpleReader;


    @Bean
    public Job jsonFileItemWriterJob() throws Exception {
        return jobBuilderFactory.get("jsonFileItemWriterJob")
            .start(step())
            .build();
    }

    private Step step() throws Exception {
        return stepBuilderFactory.get("step")
            .<TestData, TestData>chunk(2)
            .reader(simpleReader)
            .writer(jsonFileItemWriter())
            .build();
    }


    private JsonFileItemWriter<TestData> jsonFileItemWriter() throws Exception {

        FileSystemResource file = new FileSystemResource("F:\\test\\file.json");
        Path path = Paths.get(file.getPath());
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        // 将对象转换为json
        JacksonJsonObjectMarshaller<TestData> marshaller = new JacksonJsonObjectMarshaller<>();
        JsonFileItemWriter<TestData> writer = new JsonFileItemWriter<>(file, marshaller);
        // 设置别名
        writer.setName("testDatasonFileItemWriter");
        return writer;
    }


}
