package org.yxs.netty.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.yxs.netty.codec.RpcDecoder;
import org.yxs.netty.codec.RpcEncoder;
import org.yxs.netty.domain.Request;
import org.yxs.netty.domain.Response;

/**
 * @author: yang-xiansen
 * @Date: 2020/12/28 21:32
 * @Description: nettyServer接收数据
 */
public class ServerSocket implements Runnable {

    private ChannelFuture future;


    @Override
    public void run() {
        //配置服务的nio线程组
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();


        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(parentGroup, childGroup)
                .channel(NioServerSocketChannel.class) //非阻塞模式
                .option(ChannelOption.SO_BACKLOG, 128)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(
                            new RpcDecoder(Request.class),
                            new RpcEncoder(Response.class),
                            new MyServerHandler()
                        );
                    }
                });
            future = b.bind(9999).sync();
            System.out.println("服务端启动...");
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            childGroup.shutdownGracefully();
            parentGroup.shutdownGracefully();
        }

    }
}
