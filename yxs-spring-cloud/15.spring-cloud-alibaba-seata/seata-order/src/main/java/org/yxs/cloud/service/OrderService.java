package org.yxs.cloud.service;


import org.yxs.cloud.domain.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);
}
