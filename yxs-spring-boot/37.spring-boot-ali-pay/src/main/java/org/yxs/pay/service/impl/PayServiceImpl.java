package org.yxs.pay.service.impl;

import com.alipay.api.AlipayApiException;
import org.yxs.pay.entitys.AlipayBean;
import org.yxs.pay.service.PayService;
import org.yxs.pay.utils.Alipay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private Alipay alipay;


    @Override
    public String aliPay(AlipayBean alipayBean) throws AlipayApiException {
        return alipay.pay(alipayBean);
    }
}
