package org.yxs.design.store.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.yxs.design.goods.DeliverReq;
import org.yxs.design.goods.GoodsService;
import org.yxs.design.store.ICommodity;

import java.util.Map;

/**
 * @author: y-xs
 * @date: 2020/12/03 18:16
 * @description:商品发送
 */
@Slf4j
public class GoodsCommodityService implements ICommodity {
    public void sendCommodity(String userId, String commodityId, String bizId, Map<String, String> map) throws Exception {
        DeliverReq deliverReq = new DeliverReq();
        deliverReq.setUserName(queryUserName(userId));
        deliverReq.setUserPhone(queryUserPhoneNumber(userId));
        deliverReq.setSku(commodityId);
        deliverReq.setOrderId(bizId);
        deliverReq.setConsigneeUserName(map.get("consigneeUserName"));
        deliverReq.setConsigneeUserPhone(map.get("consigneeUserPhone"));
        deliverReq.setConsigneeUserAddress(map.get("consigneeUserAddress"));

        GoodsService goodsService = new GoodsService();
        Boolean isSuccess = goodsService.deliverGoods(deliverReq);

        log.info("请求参数[优惠券] => uId：{} commodityId：{} bizId：{} extMap：{}", userId, commodityId, bizId, JSON.toJSON(map));
        log.info("测试结果[优惠券]：{}", isSuccess);

        if (!isSuccess) throw new RuntimeException("实物商品发放失败");
    }

    private String queryUserName(String uId) {
        return "花花";
    }

    private String queryUserPhoneNumber(String uId) {
        return "15200101232";
    }

}
