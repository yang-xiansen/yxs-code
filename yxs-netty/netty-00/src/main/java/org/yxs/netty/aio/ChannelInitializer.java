package org.yxs.netty.aio;

import org.yxs.netty.aio.server.AioServer;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 *  @author: yang-xiansen
 *  @Date: 2020/12/01 21:44
 *  @Description: 初始化
 */
public abstract class ChannelInitializer implements CompletionHandler<AsynchronousSocketChannel, AioServer> {

    @Override
    public void completed(AsynchronousSocketChannel channel, AioServer attachment) {
        try {
            initChannel(channel);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            attachment.serverSocketChannel().accept(attachment, this);// 再此接收客户端连接
        }
    }

    @Override
    public void failed(Throwable exc, AioServer attachment) {
        exc.getStackTrace();
    }

    protected abstract void initChannel(AsynchronousSocketChannel channel) throws Exception;

}
