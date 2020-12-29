package org.yxs.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
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
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        //基于换行符的解码器 --> 发送的消息有回车键时才能接收到消息！
        socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // 基于最大长度
//        socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(4));
        //基于指定字符串（换行符 这样功能等同于LineBasedFrameDecoder）
//        socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, false, Delimiters.lineDelimiter()));

        //netty解码器 解码转String
        socketChannel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
        //netty编码器 编码码转String
        socketChannel.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
        //在管道中添加接收数据的方法
        socketChannel.pipeline().addLast(new MyServerHandler());
    }
}
