package org.yxs.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @author: y-xs
 * @date: 2020/12/29 18:22
 * @description: 配置http编码/解码器
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        //netty http解码器
        socketChannel.pipeline().addLast(new HttpRequestDecoder());
        //netty http编码器
        socketChannel.pipeline().addLast(new HttpResponseEncoder());
        //在管道中添加接收数据的方法
        socketChannel.pipeline().addLast(new MyServerHandler());
    }
}
