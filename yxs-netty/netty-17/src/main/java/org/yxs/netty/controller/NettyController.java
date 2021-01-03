package org.yxs.netty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yxs.netty.server.NettyServer;

import javax.annotation.Resource;

/**
 * @author: y-xs
 * @date: 2020/12/30 8:41
 * @description: netty控制类
 */
@Controller
public class NettyController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "xiaohao");
        return "index";
    }
}
