package org.yxs.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.yxs.netty.code.MyDecoder;
import org.yxs.netty.code.MyEncoder;

import java.nio.charset.Charset;

/**
 * @author: yang-xiansen
 * @Date: 2020/12/28 22:02
 * @Description: NettyServer使用自定义 解码/编码器
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {


        //自定义解码器
        socketChannel.pipeline().addLast(new MyDecoder());
        //自定义编码器
        socketChannel.pipeline().addLast(new MyEncoder());
        //在管道中添加接收数据的方法
        socketChannel.pipeline().addLast(new MyServerHandler());
    }
}
