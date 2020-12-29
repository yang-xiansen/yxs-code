package org.yxs.netty.code;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;

/**
 * @author: y-xs
 * @date: 2020/12/29 8:37
 * @description: 实现MessageToByteEncoder 自定义编码器 (服务器发送给客户端的消息)
 */
public class MyEncoder extends MessageToByteEncoder {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        String msg = o.toString();
        byte[] bytes = msg.getBytes(Charset.forName("GBK"));

        byte[] send = new byte[bytes.length + 2];
        System.arraycopy(bytes, 0, send, 1, bytes.length);
        send[0] = 0x02;
        send[send.length - 1] = 0x03;
        byteBuf.writeInt(send.length);
        byteBuf.writeBytes(send);

    }
}
