package org.yxs.netty.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @author: yang-xiansen
 * @Date: 2020/12/28 21:32
 * @Description: netty client
 */
public class NettyClient {

    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient();
        nettyClient.connect("127.0.0.1", 9999);
    }

    private void connect(String host, int port) {
        //配置服务的nio线程组
        EventLoopGroup workGroup = new NioEventLoopGroup();


        try {
            Bootstrap b = new Bootstrap();
            b.group(workGroup)
                    .channel(NioDatagramChannel.class)
                    .handler(new MyChannelInitializer());
            ChannelFuture channelFuture = b.connect(host, port).sync();
            Channel channel = channelFuture.channel();
            System.out.println("客户端开始链接。。。开始给udp服务器发送信息");
            channel.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("udp服务器你好，我是客户端", Charset.forName("GBK")), new InetSocketAddress(host, port)));
            channel.closeFuture().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
        }

    }
}
