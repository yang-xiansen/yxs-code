package org.yxs.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

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
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {


        //心跳检测 处理器
        // 1、readerIdleTimeSeconds 读超时时间  2、writerIdleTimeSeconds 写超时时间
        // 3、allIdleTimeSeconds    读写超时时间  TimeUnit.SECONDS 秒[默认为秒，可以指定]
        socketChannel.pipeline().addLast(new IdleStateHandler(2,2,2));

        //基于换行符的解码器 --> 发送的消息有回车键时才能接收到消息！
        socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        //netty解码器 解码转String
        socketChannel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
        //netty编码器 编码码转String
        socketChannel.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
        //在管道中添加接收数据的方法
        socketChannel.pipeline().addLast(new MyServerHandler());
    }
}
