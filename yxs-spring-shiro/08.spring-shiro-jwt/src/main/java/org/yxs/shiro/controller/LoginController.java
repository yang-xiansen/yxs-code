package org.yxs.shiro.controller;

import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.omg.CORBA.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yxs.shiro.core.Result;
import org.yxs.shiro.entity.User;
import org.yxs.shiro.service.UserService;
import org.yxs.shiro.util.JWTUtil;
import org.yxs.shiro.util.MD5Utils;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @SneakyThrows
    @PostMapping("/login")
    @ResponseBody
    public Result login(String username, String password) {
        password = MD5Utils.encrypt(username, password);

        final String errorMessage = "用户名或密码错误";
        User user = userService.findByUserName(username);

        if (user == null)
            throw new Exception(errorMessage);
        if (!StringUtils.equals(user.getPassword(), password))
            throw new Exception(errorMessage);

        // 生成 Token
        String token = JWTUtil.sign(username, password);

        Map<String, Object> userInfo = this.generateUserInfo(token, user);
        return new Result(userInfo);
    }


    /**
     * 生成前端需要的用户信息，包括：
     * 1. token
     * 2. user
     *
     * @param token token
     * @param user  用户信息
     * @return UserInfo
     */
    private Map<String, Object> generateUserInfo(String token, User user) {
        String username = user.getUserName();
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("token", token);

        user.setPassword("it's a secret");
        userInfo.put("user", user);
        return userInfo;
    }

    @RequestMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        // 登录成后，即可通过Subject获取登录的用户信息
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/403")
    public String forbid() {
        return "403";
    }
}
