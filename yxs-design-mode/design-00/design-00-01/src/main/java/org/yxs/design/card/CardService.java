package org.yxs.design.card;

/**
 * 模拟会员卡服务
 */
public class CardService {

    /**
     * @param bindMobileNumber
     * @param cardId
     * @author: y-xs
     * @date: 2020/12/03 16:45
     * @description: 模拟发送会员卡
     * @return: void
     */
    public void grantToken(String bindMobileNumber, String cardId) {
        System.out.println("模拟发放会员卡一张：" + bindMobileNumber + "，" + cardId);
    }

}
