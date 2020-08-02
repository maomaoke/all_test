package com.open.demo.socket.server;

import com.open.demo.socket.client.Client;
import com.open.demo.socket.server.handle.ClientHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenkechao
 * @date 2020/7/19 4:54 下午
 */
public class TcpServer {

    private final int port;
    private Listener listener;
    private List<ClientHandler> clientHandlerList = new ArrayList<>();

    public TcpServer(int port) {
        this.port = port;
    }

    public boolean start() {
        try {
            listener = new Listener(port);
            listener.start();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void stop() {
        if (listener != null) {
            listener.exit();
        }
        for (ClientHandler clientHandler : clientHandlerList) {
            clientHandler.exit();
        }
        clientHandlerList.clear();
    }

    public void broadcast(String str) {
        for (ClientHandler clientHandler : clientHandlerList) {
            clientHandler.send(str);
        }
    }

    private class Listener extends Thread {

        private final ServerSocket server;
        private boolean done = false;

        public Listener(int port) throws IOException {
            server = new ServerSocket(port);
            System.out.println("服务器信息: " + server.getInetAddress() + "IP: " + server.getInetAddress().getHostName()
                    + "PORT: " + server.getLocalPort());
        }

        @Override
        public void run() {
            super.run();

            System.out.println("服务器准备就绪~");

            //等待客户端连接
            do {

                Socket client = null;

                try {
                    //得到客户端
                    client = server.accept();
                } catch (IOException e) {
                    e.printStackTrace();

                }

                try {
                    ClientHandler clientHandler =
                            new ClientHandler(client, handler -> clientHandlerList.remove(handler));
                    clientHandler.readToPrint();
                    clientHandlerList.add(clientHandler);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("客户端连接异常: " + e.getMessage());
                }
                //读取数据并打印

            } while (!done);

            System.out.println("服务器已关闭!");
        }

        public void exit() {
            done = true;
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
