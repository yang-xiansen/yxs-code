package org.yxs.aop.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.yxs.aop.entity.SysLog;
import org.yxs.aop.service.SysLogService;

/**
 * @Description: 异步监听日志事件
 * @Author: yang-xiansen
 * @Date: 2020/08/17 14:54
 */
@Component
public class SysLogListener {

    @Autowired
    private SysLogService sysLogService;

    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        SysLog sysLog = (SysLog) event.getSource();
        // 保存日志
        sysLogService.add(sysLog);
    }

}
