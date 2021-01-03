package org.yxs.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.yxs.netty.domain.MsgDemo3;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MsgDemo03Handler extends SimpleChannelInboundHandler<MsgDemo3> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MsgDemo3 msgDemo3) throws Exception {
        System.out.println("\r\n> Demo3 msg handler ing ...");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收消息的处理器：" + this.getClass().getName());
        System.out.println("channelId：" + msgDemo3.getChannelId());
        System.out.println("消息内容：" + msgDemo3.getDemo3());
    }
}
