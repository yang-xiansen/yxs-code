package org.yxs.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.yxs.netty.codec.ObjDecoder;
import org.yxs.netty.codec.ObjEncoder;
import org.yxs.netty.server.handler.MsgDemo01Handler;
import org.yxs.netty.server.handler.MsgDemo02Handler;
import org.yxs.netty.server.handler.MsgDemo03Handler;

/**
 * @author: yang-xiansen
 * @Date: 2020/12/28 22:02
 * @Description: NettyServer 解码/编码 器
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        //netty自定义对象解码器
        socketChannel.pipeline().addLast(new ObjDecoder());
        //netty自定义对象编码器
        socketChannel.pipeline().addLast(new ObjEncoder());
        //在管道中添加接收数据的方法
        socketChannel.pipeline().addLast(new MsgDemo01Handler());
        socketChannel.pipeline().addLast(new MsgDemo02Handler());
        socketChannel.pipeline().addLast(new MsgDemo03Handler());
    }
}
