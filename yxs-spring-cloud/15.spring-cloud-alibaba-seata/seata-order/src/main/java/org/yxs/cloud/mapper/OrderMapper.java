package org.yxs.cloud.mapper;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.yxs.cloud.domain.Order;


@Repository
public interface OrderMapper {

    /**
     * 创建订单
     */
    void create(Order order);

    /**
     * 修改订单金额
     */
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
