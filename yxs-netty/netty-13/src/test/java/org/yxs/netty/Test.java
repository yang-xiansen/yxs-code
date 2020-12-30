package org.yxs.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public static void main(String[] args) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup);
            bootstrap.channel(NioSocketChannel.class); //非阻塞模式
            bootstrap.option(ChannelOption.AUTO_READ, true);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) throws Exception {
                    // 基于换行符号
                    channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                    // 解码转String，注意调整自己的编码格式GBK、UTF-8
                    channel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
                    // 解码转String，注意调整自己的编码格式GBK、UTF-8
                    channel.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
                    // 在管道中添加我们自己的接收数据实现方法
                    channel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            //接收msg消息{与上一章节相比，此处已经不需要自己进行解码}
                            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 客户端接收到消息：" + msg);
                        }
                    });
                }
            });

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9999).sync();
            System.out.println("客户端启动了。。。正在往服务器发送消息");

            //向服务端发送信息
            channelFuture.channel().writeAndFlush("你好，SpringBoot启动的netty服务端，我是测试客户端。“我的结尾是一个换行符，用于传输半包粘包处理”\r\n");
            channelFuture.channel().writeAndFlush("你好，SpringBoot启动的netty服务端，我是测试客户端。“我的结尾是一个换行符，用于传输半包粘包处理”\r\n");
            channelFuture.channel().writeAndFlush("你好，SpringBoot启动的netty服务端，我是测试客户端。“我的结尾是一个换行符，用于传输半包粘包处理”\r\n");
            channelFuture.channel().writeAndFlush("你好，SpringBoot启动的netty服务端，我是测试客户端。“我的结尾是一个换行符，用于传输半包粘包处理”\r\n");
            channelFuture.channel().writeAndFlush("你好，SpringBoot启动的netty服务端，我是测试客户端。“我的结尾是一个换行符，用于传输半包粘包处理”\r\n");
            channelFuture.channel().writeAndFlush("你好，SpringBoot启动的netty服务端，我是测试客户端。“我的结尾是一个换行符，用于传输半包粘包处理”\r\n");

            channelFuture.channel().closeFuture().syncUninterruptibly();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

}
