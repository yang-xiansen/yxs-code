package org.yxs.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * @author: yang-xiansen
 * @Date: 2020/12/28 22:02
 * @Description: NettyServer字符串解码器
 * netty将接收的字节码自动转换成字符串或者对象
 * 在管道中添加StringDecoder解码器
 * LineBasedFrameDecoder 基于换行符号
 * DelimiterBasedFrameDecoder 基于指定字符串
 * FixedLengthFrameDecoder 基于字符串长度
 * udp通信 核心 NioDatagramChannel、ChannelOption.SO_BROADCAST（广播）
 */
public class MyChannelInitializer extends ChannelInitializer<NioDatagramChannel> {

    private EventLoopGroup group = new NioEventLoopGroup();

    @Override
    protected void initChannel(NioDatagramChannel nioDatagramChannel) throws Exception {
        ChannelPipeline pipeline = nioDatagramChannel.pipeline();
        pipeline.addLast(group, new MyServerHandler());
    }
}
