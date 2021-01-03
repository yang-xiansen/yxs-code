package org.yxs.netty.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author: yang-xiansen
 * @Date: 2020/12/28 21:32
 * @Description: nettyServer接收数据
 */
public class NettyServer {

    public static void main(String[] args) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.bind(9999);
    }

    private void bind(int port) {
        //配置服务的nio线程组
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();


        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(parentGroup, childGroup)
                .channel(NioServerSocketChannel.class) //非阻塞模式
                .option(ChannelOption.SO_BACKLOG, 128)
                .childHandler(new MyChannelInitializer());
            ChannelFuture f = b.bind(port).sync();
            System.out.println("服务端启动");
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            childGroup.shutdownGracefully();
            parentGroup.shutdownGracefully();
        }

    }
}
