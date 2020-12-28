package org.yxs.design;

import org.yxs.design.store.ICommodity;
import org.yxs.design.store.impl.CardCommodityService;
import org.yxs.design.store.impl.CouponCommodityService;
import org.yxs.design.store.impl.GoodsCommodityService;

/**
 * @author: y-xs
 * @date: 2020/12/03 18:09
 * @description: 商店（工厂）
 */
public class StoreFactory {

    public ICommodity getCommodityService(Integer commodityType) {
        if (null == commodityType) return null;
        if (1 == commodityType) return new CouponCommodityService();
        if (2 == commodityType) return new GoodsCommodityService();
        if (3 == commodityType) return new CardCommodityService();
        throw new RuntimeException("不存在的商品服务类型");
    }

}
