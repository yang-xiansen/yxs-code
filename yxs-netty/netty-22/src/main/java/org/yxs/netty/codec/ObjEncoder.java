package org.yxs.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.yxs.netty.domain.protocol.Packet;
import org.yxs.netty.util.SerializationUtil;

/**
 * @author: y-xs
 * @date: 2020/12/30 10:03
 * @description: 自定义对象编码器
 */
public class ObjEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        byte[] data = SerializationUtil.serialize(packet);

        //增加了指令  需要加一个字节
        byteBuf.writeInt(data.length + 1);
        byteBuf.writeByte(packet.getCommand());

        byteBuf.writeBytes(data);
    }
}