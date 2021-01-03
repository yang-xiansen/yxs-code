package org.yxs.netty.server;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;
import org.yxs.netty.domain.Request;
import org.yxs.netty.domain.Response;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: yang-xiansen
 * @Date: 2020/12/28 21:45
 * @Description: 消息接收 与 给客户端发送信息
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
        System.out.println("链接开始，客户端链接。 channelId：" + socketChannel.id());
        System.out.println("链接报告Ip：" + socketChannel.localAddress().getHostString());
        System.out.println("链接报告Port:" + socketChannel.localAddress().getPort());
        System.out.println("链接报告完毕");

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Request request = (Request) msg;
        //反馈
        Response response = new Response();
        response.setRequestId(request.getRequestId());
        response.setParam(request.getResult() + " 请求成功，反馈结果请接受处理。");
        ctx.writeAndFlush(response);
        //释放
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
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
