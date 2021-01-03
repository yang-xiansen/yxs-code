package org.yxs.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: yang-xiansen
 * @Date: 2020/12/28 21:45
 * @Description: 消息接收 与 给客户端发送信息
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * @param ctx
     * @param evt
     * @Description: 事件触动
     * @return: void
     * @Author: yang-xiansen
     * @Date: 2021/01/03 17:54
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        //心跳检测事件触动
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.READER_IDLE) {
                System.out.println("Reader Idle");
                //在心跳中设置了ctx.close()模拟断开链接 等待重连
                ctx.writeAndFlush("读取等待：客户端你在吗[ctx.close()]{我结尾是一个换行符用于处理半包粘包}... ...\r\n");
                ctx.close();
            } else if (e.state() == IdleState.WRITER_IDLE) {
                System.out.println("Write Idle");
                ctx.writeAndFlush("写入等待：客户端你在吗{我结尾是一个换行符用于处理半包粘包}... ...\r\n");
            } else if (e.state() == IdleState.ALL_IDLE) {
                System.out.println("All_IDLE");
                ctx.writeAndFlush("全部时间：客户端你在吗{我结尾是一个换行符用于处理半包粘包}... ...\r\n");
            }
        }
        ctx.flush();
    }

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

        //通知客户端链接成功  ｛不需要通过ByteBuf，可以直接发送字符串｝
        String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
            + " 来自server通知 --> 建立连接成功 IP:" + socketChannel.localAddress().getHostString() + "\r\n";
        context.writeAndFlush(str);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //接收msg消息 不用手动进行解码了
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收的消息：" + msg);
        //通知客户端消息发送成功  ｛不需要通过ByteBuf，可以直接发送字符串｝
        String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
            + " 来自server通知 --> 收到客户端发来的消息：" + " " + msg + "\r\n";
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
