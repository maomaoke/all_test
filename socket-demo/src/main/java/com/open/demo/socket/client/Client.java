package com.open.demo.socket.client;

import com.open.demo.socket.client.bean.ServerInfo;

import java.io.IOException;

/**
 * @author chenkechao
 * @date 2020/7/19 12:07 下午
 */
public class Client {

    public static void main(String[] args) {
        ServerInfo info = UdpSearcher.searchServer(10000);
        System.out.println("Server: " + info);

        if (info != null) {
            try {
                TcpClient.linkWith(info);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
