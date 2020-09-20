package org.yxs.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.yxs.batch.entity.TestData;

/**
 * @Description: 数据转换
 * @Author: y-xs
 * @Date: 2020/09/20 16:03
 */
@Component
public class TestDataTransformItemPorcessor implements ItemProcessor<TestData, TestData> {
    @Override
    public TestData process(TestData item) {
        // field1值拼接 hello
        item.setField1(item.getField1() + " hello");
        return item;
    }
}
