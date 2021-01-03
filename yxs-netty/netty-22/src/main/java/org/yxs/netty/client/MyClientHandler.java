package org.yxs.netty.client;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import org.yxs.netty.util.MsgUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: y-xs
 * @date: 2020/12/29 13:40
 * @description: ChannelInboundHandler拦截和处理入站事件
 */
public class MyClientHandler extends ChannelInboundHandlerAdapter {

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
        System.out.println("链接开始，本地客户端链接服务器。channelId：" + socketChannel.id());
        System.out.println("链接报告Ip：" + socketChannel.localAddress().getHostString());
        System.out.println("链接报告Port:" + socketChannel.localAddress().getPort());
        System.out.println("链接报告完毕");
        
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //接收msg消息 不用手动进行解码了
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收到消息类型：" + msg.getClass());
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收到消息内容：" + JSON.toJSONString(msg));
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
        System.out.println("异常消息：\r\n" + cause.getMessage());
    }
}
