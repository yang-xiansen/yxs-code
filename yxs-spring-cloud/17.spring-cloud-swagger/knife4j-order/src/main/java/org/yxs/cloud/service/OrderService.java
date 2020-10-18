package org.yxs.cloud.service;


import org.yxs.cloud.domain.Order;

import java.util.List;


public interface OrderService {
    void create(Order order);

    Order getOrder(Long id);

    void update(Order order);

    void delete(Long id);
}
