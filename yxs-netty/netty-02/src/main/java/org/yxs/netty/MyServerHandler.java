package org.yxs.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author: yang-xiansen
 * @Date: 2020/12/28 21:45
 * @Description: 消息接收
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //接收msg消息
        ByteBuf buf = (ByteBuf) msg;
        byte[] msgBytes = new byte[buf.readableBytes()];
        buf.readBytes(msgBytes);
        System.out.println(new Date() + "接收到消息");
        System.out.println(new String(msgBytes, Charset.forName("GBK")));
    }

}
