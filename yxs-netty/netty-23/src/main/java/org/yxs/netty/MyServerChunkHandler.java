package org.yxs.netty;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelProgressiveFuture;
import io.netty.channel.ChannelProgressiveFutureListener;
import io.netty.channel.ChannelProgressivePromise;
import io.netty.channel.ChannelPromise;
import io.netty.handler.stream.ChunkedStream;
import io.netty.util.ReferenceCountUtil;

/**
 * @author: yang-xiansen
 * @Date: 2021/01/03 19:37
 * @Description: 流量分块
 */
public class MyServerChunkHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {

        //内容验证
        if (!(msg instanceof ByteBuf)) {
            super.write(ctx, msg, promise);
            return;
        }

        //获取byte
        ByteBuf buf = (ByteBuf) msg;
        byte[] data = this.getData(buf);

        //写入流中
        ByteInputStream inputStream = new ByteInputStream();
        inputStream.setBuf(data);

        //消息分块；10个字节
        ChunkedStream stream = new ChunkedStream(inputStream, 10);

        //管道消息传输承诺
        ChannelProgressivePromise progressivePromise = ctx.channel().newProgressivePromise();
        progressivePromise.addListener(new ChannelProgressiveFutureListener() {
            @Override
            public void operationProgressed(ChannelProgressiveFuture channelProgressiveFuture, long l, long l1) throws Exception {

            }

            @Override
            public void operationComplete(ChannelProgressiveFuture channelProgressiveFuture) throws Exception {
                if (channelProgressiveFuture.isSuccess()) {
                    System.out.println("消息发送成功，success");
                    promise.setSuccess();
                } else {
                    System.out.println("消息发送成功，failure " + channelProgressiveFuture.cause());
                    promise.setFailure(channelProgressiveFuture.cause());
                }
            }
        });
        ReferenceCountUtil.release(msg);
        ctx.write(stream, progressivePromise);

    }

    /**
     * @param byteBuf
     * @Description: 获取byte
     * @return: byte[]
     * @Author: yang-xiansen
     * @Date: 2021/01/03 19:40
     */
    private byte[] getData(ByteBuf byteBuf) {
        if (byteBuf.hasArray()) {
            return byteBuf.array().clone();
        }
        byte[] data = new byte[byteBuf.readableBytes() - 1];
        byteBuf.readBytes(data);
        return data;
    }
}
