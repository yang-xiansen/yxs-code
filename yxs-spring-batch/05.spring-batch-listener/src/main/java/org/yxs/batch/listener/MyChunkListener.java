package org.yxs.batch.listener;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

/**
 * @Description: chunk监听器
 * @Author: y-xs
 * @Date: 2020/09/21 8:50
 */
@Component
public class MyChunkListener implements ChunkListener {

    @Override
    public void beforeChunk(ChunkContext context) {
        System.out.println("before chunk: " + context.getStepContext().getStepName());
    }

    @Override
    public void afterChunk(ChunkContext context) {
        System.out.println("after chunk: " + context.getStepContext().getStepName());
    }

    @Override
    public void afterChunkError(ChunkContext context) {
        System.out.println("before chunk error: " + context.getStepContext().getStepName());
    }

}
