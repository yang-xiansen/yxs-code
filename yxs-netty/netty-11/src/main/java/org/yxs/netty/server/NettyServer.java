package org.yxs.netty.server;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * @author: yang-xiansen
 * @Date: 2020/12/28 21:32
 * @Description: netty udp通信 核心 NioDatagramChannel、ChannelOption.SO_BROADCAST（广播）
 * Internet 协议集支持一个无连接的传输协议，该协议称为用户数据报协议（UDP，User Datagram Protocol）
 */
public class NettyServer {

    public static void main(String[] args) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.bind(9999);
    }

    private void bind(int port) {
        //配置服务的nio线程组
        EventLoopGroup group = new NioEventLoopGroup();


        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true) //广播
                    .option(ChannelOption.SO_RCVBUF, 2048 * 1024)  // 设置UDP读缓冲区为2M
                    .option(ChannelOption.SO_SNDBUF, 1024 * 1024)  // 设置UDP写缓冲区为1M
                    .handler(new MyChannelInitializer());
            ChannelFuture f = b.bind(port).sync();
            System.out.println("服务端启动");
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }
}
