package org.yxs.cloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.yxs.cloud.core.CommonResult;

/**
 * @Description: 自定义限流处理逻辑
 * @Author: y-xs
 * @Date: 2020/10/12 10:48
 */
public class CustomBlockHandler {

    public CommonResult handleException(BlockException exception) {
        return new CommonResult("自定义限流信息", 200);
    }
}
