package org.yxs.cloud.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yxs.cloud.domain.CommonResult;

@FeignClient(value = "seata-storage")
public interface StorageService {

    /**
     * 扣减库存
     */
    @GetMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
