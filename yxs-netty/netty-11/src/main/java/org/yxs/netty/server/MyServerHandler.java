package org.yxs.netty.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.SocketChannel;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: yang-xiansen
 * @Date: 2020/12/28 21:45
 * @Description: 消息接收 与 给客户端发送信息
 */
public class MyServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        String msg = datagramPacket.touch().content().toString(Charset.forName("GBK"));

        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " UDP服务器接收的消息：" + msg);
        //通知客户端消息发送成功
        String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
                + " 来自server通知 --> 收到客户端发来的消息：" + " " + msg + "\r\n";
        //数据报的格式是以字符数组形式存储的
        byte[] bytes = str.getBytes(Charset.forName("GBK"));
        DatagramPacket data = new DatagramPacket(Unpooled.copiedBuffer(bytes), datagramPacket.sender());
        channelHandlerContext.writeAndFlush(data);
    }

}
