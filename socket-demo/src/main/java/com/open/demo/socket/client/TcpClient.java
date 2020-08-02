package com.open.demo.socket.client;

import com.open.demo.socket.client.bean.ServerInfo;
import com.open.demo.socket.server.handle.ClientHandler;
import com.open.demo.socket.utils.CloseUtils;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author chenkechao
 * @date 2020/7/19 6:44 下午
 */
public class TcpClient {

    public static void linkWith(ServerInfo info) throws IOException {
        Socket socket = new Socket();
        //设置超时时间
        socket.setSoTimeout(3000);

        //连接服务器socket; 超时时间3000ms
        socket.connect(new InetSocketAddress(info.getAddress(), info.getPort()), 3000);

        System.out.println("已发起服务器连接, 并进入后续流程~");
        System.out.println("客户端信息: " + socket.getLocalAddress() + " P:" + socket.getLocalPort());
        System.out.println("服务器信息: " + socket.getInetAddress() + " P:" + socket.getPort());

        try {

            ReadHandler readHandler = new ReadHandler(socket.getInputStream());
            readHandler.start();

            write(socket);

            readHandler.exit();

        } catch (Exception e) {
            System.out.println("异常关闭");
        }

        socket.close();
        System.out.println("客户端已退出~");
    }

    private static void write(Socket socket) throws Exception {

        //构建键盘输入流
        InputStream in = System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(in));

        // 得到Socket输出流, 并转化为打印流
        OutputStream outputStream = socket.getOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        do {
            //键盘读取一行
            String str = input.readLine();
            //发送到服务器
            printStream.println(str);

            if ("00bye00".equalsIgnoreCase(str)) {
                break;
            }
        } while (true);

        printStream.close();
    }

    static private class ReadHandler extends Thread {

        private boolean done = false;
        private final InputStream inputStream;

        public ReadHandler(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public void run() {
            super.run();
            try {

                BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));

                do {
                    //客户端拿到一条数据
                    String str = input.readLine();
                    if (str == null) {
                        System.out.println("链接已关闭, 无法读取数据!");
                        //退出当前客户端
                        break;
                    }
                    System.out.println(str);
                } while (!done);
            } catch (IOException e) {
                if (!done) {
                    System.out.println("连接异常断开");
                }
            } finally {
                CloseUtils.close(inputStream);
            }
        }

        void exit() {
            done = true;
            CloseUtils.close(inputStream);
        }
    }
}
