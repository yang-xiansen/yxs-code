package org.yxs.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.Charset;

public abstract class ChannelAdapter extends Thread {

    private Socket socket;

    private Charset charset;

    private ChannelHandler channelHandler;

    public ChannelAdapter(Socket socket, Charset charset) {
        this.socket = socket;
        this.charset = charset;
        while (!socket.isConnected()) {
            break;
        }
        channelHandler = new ChannelHandler(socket, charset);
        channelActive(channelHandler);
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), charset));
            String str = null;
            while ((str = input.readLine()) != null) {
                channelRead(channelHandler, str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param channelHandler
     * @Description: 通知对方已链接上
     * @return: void
     * @Author: yang-xiansen
     * @Date: 2020/12/20 22:32
     */
    public abstract void channelActive(ChannelHandler channelHandler);

    /**
     * @param channelHandler
     * @param msg
     * @Description: 接收已收到消息，并且通知对方已收到
     * @return: void
     * @Author: yang-xiansen
     * @Date: 2020/12/20 22:33
     */
    public abstract void channelRead(ChannelHandler channelHandler, Object msg);

}
