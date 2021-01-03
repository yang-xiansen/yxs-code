package org.yxs.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: yang-xiansen
 * @Date: 2020/12/28 21:45
 * @Description: 服务端消息接收 与 给客户端发送信息
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * @param context
     * @Description: 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
     * @return: void
     * @Author: yang-xiansen
     * @Date: 2020/12/29 6:37
     */
    @Override
    public void channelActive(ChannelHandlerContext context) throws Exception {

        SocketChannel socketChannel = (SocketChannel) context.channel();
        System.out.println("链接开始，客户端链接");
        System.out.println("链接报告Ip：" + socketChannel.localAddress().getHostString());
        System.out.println("链接报告Port:" + socketChannel.localAddress().getPort());
        System.out.println("链接报告完毕");

        //单独通知客户端链接成功  ｛不需要通过ByteBuf，可以直接发送字符串｝
        String str = "通知客户端建立连接成功 " + new Date() + " " + socketChannel.localAddress().getHostString() + "\r\n";
        context.writeAndFlush(str);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //接收msg消息 不用手动进行解码了
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "接收的消息：" + msg);
        //通知客户端消息发送成功  ｛不需要通过ByteBuf，可以直接发送字符串｝
        String str = new Date() + "服务端收到客户端发来的消息：" + " " + msg + "\r\n";
        //接收到消息 发送消息给客户端！
        ctx.writeAndFlush(str);
    }

    /**
     * @param ctx
     * @Description: 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
     * @return: void
     * @Author: yang-xiansen
     * @Date: 2020/12/28 22:24
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开链接 " + ctx.channel().localAddress().toString());
    }

    /**
     * @param ctx
     * @param cause
     * @Description: 捕获异常，当发生异常的时候，可以做一些相应的处理，比如打印日志、关闭链接
     * @return: void
     * @Author: yang-xiansen
     * @Date: 2020/12/29 6:37
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常消息：" + cause.getMessage());
    }
}
