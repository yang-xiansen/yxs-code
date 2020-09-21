package org.yxs.batch.listener;

import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;

/**
 * @Description: skip监听器
 * @Author: y-xs
 * @Date: 2020/09/21 10:37
 */
@Component
public class MySkipListener implements SkipListener<String, String> {
    @Override
    public void onSkipInRead(Throwable t) {
        System.out.println("在读取数据的时候遇到异常并跳过，异常：" + t.getMessage());
    }

    @Override
    public void onSkipInWrite(String item, Throwable t) {
        System.out.println("在输出数据的时候遇到异常并跳过，待输出数据：" + item + "，异常：" + t.getMessage());
    }

    @Override
    public void onSkipInProcess(String item, Throwable t) {
        System.out.println("在处理数据的时候遇到异常并跳过，待输出数据：" + item + "，异常：" + t.getMessage());
    }
}
