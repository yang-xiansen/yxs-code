package org.yxs.batch.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.Iterator;
import java.util.List;

/**
 * @Description: 简单数据读取
 * @Author: y-xs
 * @Date: 2020/09/19 10:02
 */
public class SimpleItemReader implements ItemReader<String> {

    private Iterator<String> iterator;

    public SimpleItemReader(List<String> data) {
        this.iterator = data.iterator();
    }


    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return iterator.hasNext() ? iterator.next() : null;
    }
}
