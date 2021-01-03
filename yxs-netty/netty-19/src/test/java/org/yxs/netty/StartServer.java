package org.yxs.netty;


import org.yxs.netty.server.ServerSocket;

public class StartServer {

    public static void main(String[] args) {
        new Thread(new ServerSocket()).start();
    }

}
