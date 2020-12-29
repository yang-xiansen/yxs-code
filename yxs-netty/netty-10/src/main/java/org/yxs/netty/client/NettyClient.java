package org.yxs.netty.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

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
                .channel(NioSocketChannel.class) //非阻塞模式
                .option(ChannelOption.AUTO_READ, true)
                .handler(new MyChannelInitializer());
            ChannelFuture f = b.connect(host, port).sync();
            System.out.println("客户端开始链接。。。");
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
        }

    }
}
