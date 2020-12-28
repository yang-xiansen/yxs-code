package org.yxs.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        System.out.println("链接开始......");
        System.out.println("本地客户端连接到服务端，channelId：:" + socketChannel.id());
        System.out.println("链接报告完毕");
    }
}
