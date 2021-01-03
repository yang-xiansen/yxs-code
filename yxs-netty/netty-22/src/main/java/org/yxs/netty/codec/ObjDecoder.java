package org.yxs.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.yxs.netty.domain.protocol.PacketClazzMap;
import org.yxs.netty.util.SerializationUtil;

import java.util.List;

/**
 * @author: y-xs
 * @date: 2020/12/30 10:03
 * @description: 自定义对象解码器
 */
public class ObjDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        if (byteBuf.readableBytes() < 4) {
            return;
        }

        byteBuf.markReaderIndex();

        int dataLength = byteBuf.readInt();

        if (byteBuf.readableBytes() < dataLength) {
            byteBuf.resetReaderIndex();
            return;
        }

        //读取指令
        byte command = byteBuf.readByte();

        // 指令占了一位 删除掉
        byte[] data = new byte[dataLength - 1];
        byteBuf.readBytes(data);
        list.add(SerializationUtil.deserialize(data, PacketClazzMap.packetTypeMap.get(command)));


    }
}
