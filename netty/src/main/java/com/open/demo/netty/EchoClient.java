package com.open.demo.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @author chenkechao
 * @date 2019/11/30 11:36 上午
 */
public class EchoClient {
    public void start() throws IOException {

        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 9999);
        //client socket
        SocketChannel socketChannel = SocketChannel.open();
        //绑定地址
        socketChannel.bind(socketAddress);
        //设置成非堵塞
        socketChannel.configureBlocking(false);
        while (!socketChannel.finishConnect()) {
        }

    }
}
