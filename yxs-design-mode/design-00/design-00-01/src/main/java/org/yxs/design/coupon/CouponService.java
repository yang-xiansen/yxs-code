package org.yxs.design.coupon;

/**
 * 模拟优惠券服务
 */
public class CouponService {

    /**
     * @param uId
     * @param couponNumber
     * @param uuid
     * @author: y-xs
     * @date: 2020/12/03 16:46
     * @description: 模拟发送优惠券
     * @return: org.yxs.design.coupon.CouponResult
     */
    public CouponResult sendCoupon(String uId, String couponNumber, String uuid) {
        System.out.println("模拟发放优惠券一张：" + uId + "," + couponNumber + "," + uuid);
        return new CouponResult("0000", "发放成功");
    }

}
