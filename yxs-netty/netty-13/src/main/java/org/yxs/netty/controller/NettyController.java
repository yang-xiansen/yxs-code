package org.yxs.netty.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yxs.netty.server.NettyServer;

import javax.annotation.Resource;

/**
 * @author: y-xs
 * @date: 2020/12/30 8:41
 * @description: netty控制类
 */
@RestController
@RequestMapping(value = "/netty")
public class NettyController {

    @Resource
    private NettyServer nettyServer;

    @RequestMapping("/localAddress")
    public String localAddress() {
        return "nettyServer localAddress " + nettyServer.getChannel().localAddress();
    }

    @RequestMapping("/isOpen")
    public String isOpen() {
        return "nettyServer isOpen " + nettyServer.getChannel().isOpen();
    }

}
