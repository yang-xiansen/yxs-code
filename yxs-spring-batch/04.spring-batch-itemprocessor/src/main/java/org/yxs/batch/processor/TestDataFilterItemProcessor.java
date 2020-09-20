package org.yxs.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.yxs.batch.entity.TestData;

/**
 * @Description: 数据过滤
 * @Author: y-xs
 * @Date: 2020/09/19 17:59
 */
@Component
public class TestDataFilterItemProcessor implements ItemProcessor<TestData, TestData> {

    @Override
    public TestData process(TestData data) throws Exception {
        // 返回null，会过滤掉这条数据
        return "".equals(data.getField3()) ? null : data;
    }
}
