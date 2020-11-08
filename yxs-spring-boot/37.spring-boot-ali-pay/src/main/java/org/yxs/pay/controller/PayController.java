package org.yxs.pay.controller;

import com.alipay.api.AlipayApiException;
import org.springframework.web.bind.annotation.PostMapping;
import org.yxs.pay.entitys.AlipayBean;
import org.yxs.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
     *
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

    /**
     * @author: yang-xiansen
     * @Date: 2020/10/19 19:46
     * @Description:支付宝支付结果通知
     */
    @PostMapping(value = "/notify")
    public void notify(HttpServletRequest request) throws AlipayApiException {
        System.out.println("异步回调成功");
    }

    /**
     * @author: yang-xiansen
     * @Date: 2020/10/19 19:47
     * @Description:支付成功跳转页面
     */
    @GetMapping(value = "/pay/success")
    public String paySuccess(HttpServletRequest request) throws AlipayApiException {
        System.out.println("同步回调成功");
        return "同步回调成功";
    }

}
