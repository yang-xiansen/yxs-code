package org.yxs.security.controller;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.yxs.security.entity.SmsCode;
import org.yxs.security.service.RedisCodeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ValidateController {

    @Autowired
    private RedisCodeService redisCodeService;

    @GetMapping("/code/sms")
    public String createSmsCode(HttpServletRequest request, HttpServletResponse response, String mobile) throws Exception {
        SmsCode smsCode = createSMSCode();
        redisCodeService.save(smsCode, new ServletWebRequest(request), mobile);
        // 输出验证码到控制台代替短信发送服务
        return "您的登录验证码为：" + smsCode.getCode() + "，有效时间为60秒";
    }

    private SmsCode createSMSCode() {
        String code = RandomStringUtils.randomNumeric(6);
        return new SmsCode(code, 60);
    }

}
