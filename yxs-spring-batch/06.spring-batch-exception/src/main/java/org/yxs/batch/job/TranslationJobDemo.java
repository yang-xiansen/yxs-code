package org.yxs.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.yxs.batch.exception.MyJobExecutionException;
import org.yxs.batch.listener.MySkipListener;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * @Description: 事物问题
 * @Author: y-xs
 * @Date: 2020/09/21 9:53
 */
@Component
public class TranslationJobDemo {


    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job translationJob() {
        return jobBuilderFactory.get("translationJob")
            .start(step())
            .build();
    }

    private Step step() {

        DefaultTransactionAttribute attribute = new DefaultTransactionAttribute();
        attribute.setPropagationBehavior(Propagation.REQUIRED.value());  //设置传播方式
        attribute.setIsolationLevel(Isolation.DEFAULT.value());   //隔离级别
        attribute.setTimeout(30);  //过期时间


        return stepBuilderFactory.get("step")
            .<String, String>chunk(2)
            .reader(listItemReader())
            .writer(list -> list.forEach(System.out::println))
            .readerIsTransactionalQueue() // 消息队列数据重读
            .transactionAttribute(attribute) //设置事物
            .build();
    }

    private ListItemReader<String> listItemReader() {
        ArrayList<String> datas = new ArrayList<>();
        IntStream.range(0, 5).forEach(i -> datas.add(String.valueOf(i)));
        return new ListItemReader<>(datas);
    }

}
