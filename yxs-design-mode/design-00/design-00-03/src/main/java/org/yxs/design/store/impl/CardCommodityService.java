package org.yxs.design.store.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.yxs.design.card.CardService;
import org.yxs.design.store.ICommodity;

import java.util.Map;

/**
 * @author: y-xs
 * @date: 2020/12/03 18:15
 * @description: 第三方会员卡发放
 */
@Slf4j
public class CardCommodityService implements ICommodity {
    public void sendCommodity(String userId, String commodityId, String bizId, Map<String, String> map) throws Exception {
        String mobile = queryUserMobile(userId);
        CardService cardService = new CardService();
        cardService.grantToken(mobile, bizId);
        log.info("请求参数[会员卡] => uId：{} commodityId：{} bizId：{} extMap：{}", userId, commodityId, bizId, JSON.toJSON(map));
        log.info("测试结果[会员卡]：success");
    }

    private String queryUserMobile(String uId) {
        return "15200101232";
    }
}
