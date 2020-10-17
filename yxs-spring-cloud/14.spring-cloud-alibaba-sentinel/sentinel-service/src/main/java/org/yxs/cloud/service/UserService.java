package org.yxs.cloud.service;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.yxs.cloud.core.CommonResult;
import org.yxs.cloud.domain.User;
import org.yxs.cloud.service.impl.UserFallbackService;

/**
 * @Description: feign远程调用
 * @Author: y-xs
 * @Date: 2020/10/12 12:22
 */
@FeignClient(value = "nacos-user-service", fallback = UserFallbackService.class)
public interface UserService {
    @PostMapping("/user/create")
    CommonResult create(@RequestBody User user);

    @GetMapping("/user/{id}")
    CommonResult<User> getUser(@PathVariable Long id);

    @GetMapping("/user/getByUsername")
    CommonResult<User> getByUsername(@RequestParam String username);

    @PostMapping("/user/update")
    CommonResult update(@RequestBody User user);

    @PostMapping("/user/delete/{id}")
    CommonResult delete(@PathVariable Long id);
}
