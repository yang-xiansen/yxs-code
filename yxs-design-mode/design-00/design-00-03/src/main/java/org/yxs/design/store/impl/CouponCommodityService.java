package org.yxs.design.store.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.yxs.design.coupon.CouponResult;
import org.yxs.design.coupon.CouponService;
import org.yxs.design.store.ICommodity;

import java.util.Map;

/**
 * @author: y-xs
 * @date: 2020/12/03 18:16
 * @description:优惠券发放
 */
@Slf4j
public class CouponCommodityService implements ICommodity {
    public void sendCommodity(String userId, String commodityId, String bizId, Map<String, String> map) throws Exception {

        CouponService couponService = new CouponService();
        CouponResult couponResult = couponService.sendCoupon(userId, commodityId, bizId);
        log.info("请求参数[优惠券] => uId：{} commodityId：{} bizId：{} extMap：{}", userId, commodityId, bizId, JSON.toJSON(map));
        log.info("测试结果[优惠券]：{}", JSON.toJSON(couponResult));
        if (!"0000".equals(couponResult.getCode())) throw new RuntimeException(couponResult.getInfo());
    }
}
