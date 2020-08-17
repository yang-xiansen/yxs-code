package org.yxs.aop.event;

import org.springframework.context.ApplicationEvent;
import org.yxs.aop.entity.SysLog;

/**
 * @Description: 系统日志事件
 * @Author: yang-xiansen
 * @Date: 2020/08/17 14:53
 */
public class SysLogEvent extends ApplicationEvent {
    public SysLogEvent(SysLog source) {
        super(source);
    }
}
