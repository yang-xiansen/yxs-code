package org.yxs.netty.bio.client;


import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;

public class BioClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 9999);
            BioClientHandler bioClientHandler = new BioClientHandler(socket, Charset.forName("utf-8"));
            bioClientHandler.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
