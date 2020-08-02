package com.open.demo.socket.server;

import com.open.demo.socket.constants.TcpConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author chenkechao
 * @date 2020/7/19 10:34 上午
 */
public class Server {

    public static void main(String[] args) throws IOException {

        TcpServer tcpServer = new TcpServer(TcpConstants.PORT_SERVER);
        boolean isSucceed = tcpServer.start();
        if (!isSucceed) {
            System.out.println("Start TCP server failed");
            return;
        }

        UdpProvider.start(TcpConstants.PORT_SERVER);

        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

        String str;
        do {
            str = br.readLine();
            tcpServer.broadcast(str);
        } while ("00bye00".equalsIgnoreCase(str));


        UdpProvider.stop();
        tcpServer.stop();
    }
}
