package org.yxs.netty.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class NioClient {

    public static void main(String[] args) throws IOException {
        //选择器
        Selector selector = Selector.open();
        //通道
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        boolean isConnect = socketChannel.connect(new InetSocketAddress("127.0.0.1", 9999));
        if (isConnect) {
            //注册选择器
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
        System.out.println("nio客户端");
        new NioClientHandler(selector, Charset.forName("GBK")).start();

    }
}
