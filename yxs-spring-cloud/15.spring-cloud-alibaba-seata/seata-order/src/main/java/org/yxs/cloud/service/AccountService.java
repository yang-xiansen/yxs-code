package org.yxs.cloud.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yxs.cloud.domain.CommonResult;

import java.math.BigDecimal;

@FeignClient(value = "seata-account")
public interface AccountService {

    /**
     * 扣减账户余额
     */
    @RequestMapping("/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
