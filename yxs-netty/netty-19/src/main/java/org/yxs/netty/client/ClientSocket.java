package org.yxs.netty.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.yxs.netty.codec.RpcDecoder;
import org.yxs.netty.codec.RpcEncoder;
import org.yxs.netty.domain.Request;
import org.yxs.netty.domain.Response;

/**
 * @author: yang-xiansen
 * @Date: 2020/12/28 21:32
 * @Description: netty client
 */
public class ClientSocket implements Runnable {

    private ChannelFuture future;

    @Override
    public void run() {
        //配置服务的nio线程组
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workGroup)
                .channel(NioSocketChannel.class) //非阻塞模式
                .option(ChannelOption.AUTO_READ, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        channel.pipeline().addLast(
                            new RpcDecoder(Response.class),
                            new RpcEncoder(Request.class),
                            new MyClientHandler()
                        );
                    }
                });

            //链接
            ChannelFuture f = b.connect("127.0.0.1", 9999).sync();
            this.future = f;
            System.out.println("客户端开始链接。。。");
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
        }
    }

    public ChannelFuture getFuture() {
        return future;
    }

    public void setFuture(ChannelFuture future) {
        this.future = future;
    }
}
