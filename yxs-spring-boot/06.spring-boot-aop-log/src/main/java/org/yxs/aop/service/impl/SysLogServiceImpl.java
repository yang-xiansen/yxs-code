package org.yxs.aop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yxs.aop.entity.SysLog;
import org.yxs.aop.mapper.SysLogMapper;
import org.yxs.aop.service.SysLogService;

@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public int add(SysLog sysLog) {
        return sysLogMapper.add(sysLog);
    }
}
