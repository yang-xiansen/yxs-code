package org.yxs.netty.aio.client;

import java.util.concurrent.Future;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;

/**
 * @author: yang-xiansen
 * @Date: 2020/12/01 21:28
 * @Description: 客户端
 */
public class AioClient {
    public static void main(String[] args) throws Exception {
        //打开通道
        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
        //建立链接
        Future<Void> future = socketChannel.connect(new InetSocketAddress("127.0.0.1", 7777));

        future.get();
        //读取消息
        socketChannel.read(ByteBuffer.allocate(1024), null, new AioClientHandler(socketChannel, Charset.forName("GBK")));
        Thread.sleep(100000);

    }
}
