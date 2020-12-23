package org.yxs.netty.bio.client;

import org.yxs.netty.bio.ChannelAdapter;
import org.yxs.netty.bio.ChannelHandler;

import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BioClientHandler extends ChannelAdapter {
    public BioClientHandler(Socket socket, Charset charset) {
        super(socket, charset);
    }

    @Override
    public void channelActive(ChannelHandler channelHandler) {
        System.out.println("链接报告LocalAddress:" + channelHandler.socket().getLocalAddress());
        channelHandler.writeAndFlush("hi! BioClient to msg for you \r\n");
    }

    @Override
    public void channelRead(ChannelHandler channelHandler, Object msg) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收到消息：" + msg);
        channelHandler.writeAndFlush("hi 我已经收到你的消息Success！\r\n");
    }
}
