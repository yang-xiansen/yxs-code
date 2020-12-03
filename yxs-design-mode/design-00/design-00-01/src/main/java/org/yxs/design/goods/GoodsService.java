package org.yxs.design.goods;

import com.alibaba.fastjson.JSON;

/**
 * 模拟实物商品服务
 */
public class GoodsService {

    /**
     * @param req
     * @author: y-xs
     * @date: 2020/12/03 16:52
     * @description: 模拟发送实物商品
     * @return: java.lang.Boolean
     */
    public Boolean deliverGoods(DeliverReq req) {
        System.out.println("模拟发货实物商品一个：" + JSON.toJSONString(req));
        return true;
    }

}
