package org.yxs.cloud.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yxs.cloud.mapper.AccountMapper;
import org.yxs.cloud.service.AccountService;

import java.math.BigDecimal;

/**
 * @Description: 账户业务实现类
 * @Author: y-xs
 * @Date: 2020/10/13 15:27
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    private AccountMapper accountMapper;

    /**
     * 扣减账户余额
     */
    @Override
    public void decrease(Long userId, BigDecimal money) {
        LOGGER.info("------->account-service中扣减账户余额开始");
        //模拟超时异常，全局事务回滚
//        try {
//            Thread.sleep(30*1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        accountMapper.decrease(userId, money);
        LOGGER.info("------->account-service中扣减账户余额结束");
    }
}
