package org.yxs.design.store;

import java.util.Map;

/**
 * @author: y-xs
 * @date: 2020/12/03 18:10
 * @description: 发送商品的接口
 */
public interface ICommodity {

    /**
     * @param userId      用户id
     * @param commodityId 商品id
     * @param bizId       业务id
     * @param map         扩展字段
     * @author: y-xs
     * @date: 2020/12/03 18:11
     * @description: 发送商品
     * @return: void
     */
    void sendCommodity(String userId, String commodityId, String bizId, Map<String, String> map) throws Exception;

}
