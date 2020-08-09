package org.yxs.wj.controller;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;
import org.yxs.wj.core.Result;
import org.yxs.wj.core.ResultFactory;
import org.yxs.wj.entity.User;
import org.yxs.wj.service.UserService;

@Controller
public class UserController {

    private UserService userService;





}
