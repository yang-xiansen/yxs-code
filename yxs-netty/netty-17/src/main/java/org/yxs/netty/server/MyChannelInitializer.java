package org.yxs.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author: yang-xiansen
 * @Date: 2021/01/02 20:26
 * @Description: websocket处理协议
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {

        socketChannel.pipeline().addLast("http-codec", new HttpServerCodec());
        socketChannel.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
        socketChannel.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
        //在管道中添加接收数据的方法
        socketChannel.pipeline().addLast(new MyServerHandler());
    }
}
