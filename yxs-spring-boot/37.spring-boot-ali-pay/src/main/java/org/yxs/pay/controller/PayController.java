package org.yxs.pay.controller;

import com.alipay.api.AlipayApiException;
import org.yxs.pay.entitys.AlipayBean;
import org.yxs.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付宝沙箱测试
 *
 * @author 小道仙
 * @date 2020年2月17日
 */
@RestController
public class PayController {

    @Autowired
    private PayService payService;

    /**
     * 阿里支付
     * @param tradeNo
     * @param subject
     * @param amount
     * @param body
     * @return
     * @throws AlipayApiException
     */
    @GetMapping(value = "order/alipay")
    public String alipay(
            @RequestParam String outTradeNo,
            @RequestParam String subject,
            @RequestParam String totalAmount,
            @RequestParam String body) throws AlipayApiException {
        AlipayBean alipayBean = new AlipayBean();
        alipayBean.setOut_trade_no(outTradeNo);
        alipayBean.setSubject(subject);
        alipayBean.setTotal_amount(totalAmount);
        alipayBean.setBody(body);
        return payService.aliPay(alipayBean);
    }

}
