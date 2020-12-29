package org.yxs.netty.code;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author: y-xs
 * @date: 2020/12/29 8:37
 * @description: 实现ByteToMessageDecoder 自定义解码器 (接收客户端的消息)
 * 定开始符号02、结束符号03；
 * 整包数据；02 89 78 54 03
 * 半包数据；02 89 78
 * 粘包数据；02 89 78 54 03 02 89
 */
public class MyDecoder extends ByteToMessageDecoder {

    //数据包基础长度
    private final int BASE_LENGTH = 4;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < BASE_LENGTH) {
            return;
        }

        //记录包头位置
        int beginIndex;

        while (true) {

            //获取包头开始的index
            beginIndex = byteBuf.readerIndex();
            //标记包头开始的index
            byteBuf.markReaderIndex();
            //读到了协议开始的标志 结束while循环
            if (byteBuf.readByte() == 0x02) {
                break;
            }

            //没有读到包头 略过一个字节
            //每次略过，一个字节，去读取，包头信息的开始标记
            byteBuf.resetReaderIndex();
            byteBuf.readByte();

            // 当略过，一个字节之后，
            // 数据包的长度，又变得不满足
            // 此时，应该结束。等待后面的数据到达
            if (byteBuf.readableBytes() < BASE_LENGTH) {
                return;
            }
        }

        //剩余长度不足可读取数量[没有内容长度位]
        int readableCount = byteBuf.readableBytes();
        if (readableCount <= 1) {
            byteBuf.readerIndex(beginIndex);
            return;
        }


        //长度域占4字节，读取int
        ByteBuf b = byteBuf.readBytes(1);
        String msgLengthStr = b.toString(Charset.forName("GBK"));
        int msgLength = Integer.parseInt(msgLengthStr);

        //剩余长度不足可读取数量[没有结尾标识]
        readableCount = byteBuf.readableBytes();
        if (readableCount < msgLength + 1) {
            byteBuf.readerIndex(beginIndex);
            return;
        }

        ByteBuf msgContent = byteBuf.readBytes(msgLength);

        //如果没有结尾标识，还原指针位置[其他标识结尾]
        byte end = byteBuf.readByte();
        if (end != 0x03) {
            byteBuf.readerIndex(beginIndex);
            return;
        }

        list.add(msgContent.toString(Charset.forName("GBK")));

    }
}
