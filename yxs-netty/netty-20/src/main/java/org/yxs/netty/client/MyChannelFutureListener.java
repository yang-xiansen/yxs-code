package org.yxs.netty.client;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;

import java.util.concurrent.TimeUnit;

/**
 * @author: yang-xiansen
 * @Date: 2021/01/03 17:39
 * @Description: client监听器
 */
public class MyChannelFutureListener implements ChannelFutureListener {
    @Override
    public void operationComplete(ChannelFuture channelFuture) throws Exception {
        if (channelFuture.isSuccess()) {
            System.out.println("client is connecting......");
            return;
        }
        final EventLoop loop = channelFuture.channel().eventLoop();
        //断线重连
        loop.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    new NettyClient().connect("127.0.0.1", 9999);
                    System.out.println("client restart done.....");
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.out.println("client restart error..... ");
                }
            }
        }, 1L, TimeUnit.SECONDS);
    }
}
