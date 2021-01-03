package org.yxs.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.yxs.netty.domain.MsgDemo1;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MsgDemo01Handler extends SimpleChannelInboundHandler<MsgDemo1> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MsgDemo1 msgDemo1) throws Exception {
        System.out.println("\r\n> Demo1 msg handler ing ...");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收消息的处理器：" + this.getClass().getName());
        System.out.println("channelId：" + msgDemo1.getChannelId());
        System.out.println("消息内容：" + msgDemo1.getDemo1());
    }
}
