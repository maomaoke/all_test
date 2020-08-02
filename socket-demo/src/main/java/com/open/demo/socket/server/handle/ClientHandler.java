package com.open.demo.socket.server.handle;

import com.open.demo.socket.utils.CloseUtils;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 在server中 用来处理 client socket的类
 * @author chenkechao
 * @date 2020/7/26 6:21 下午
 */
public class ClientHandler {
    private final Socket socket;
    private final ClientReadHandler readHandler;
    private final ClientWriteHandler writeHandler;
    private final CloseNotify closeNotify;

    public ClientHandler(Socket socket, CloseNotify closeNotify) throws IOException {
        this.socket = socket;
        this.closeNotify = closeNotify;
        this.readHandler = new ClientReadHandler(socket.getInputStream());
        this.writeHandler = new ClientWriteHandler(socket.getOutputStream());
        System.out.println("新客户端连接: " + socket.getInetAddress() + "IP: " + socket.getInetAddress().getHostName()
                + "PORT: " + socket.getPort());
    }




    public void send(String str) {
        writeHandler.send(str);
    }

    public void readToPrint() {
        readHandler.start();
    }

    public void exit() {
        readHandler.exit();
        writeHandler.exit();
        CloseUtils.close(socket);
        System.out.println("客户端已退出: " + socket.getInetAddress());
    }

    private void exitBySelf() {
        exit();
        closeNotify.onSelfClosed(this);
    }

    @FunctionalInterface
    public interface CloseNotify {
        /**
         *
         * @param clientHandler
         */
        void onSelfClosed(ClientHandler clientHandler);
    }

    private class ClientReadHandler extends Thread {

        private boolean done = false;
        private final InputStream inputStream;

        public ClientReadHandler(InputStream inputStream) {
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
                        System.out.println("客户端已无法读取数据!");
                        //退出当前客户端
                        ClientHandler.this.exitBySelf();
                        break;
                    }
                    System.out.println(str);
                } while (!done);
            } catch (IOException e) {
                if (!done) {
                    System.out.println("连接异常断开");
                    ClientHandler.this.exitBySelf();
                }
                e.printStackTrace();
                System.out.println("连接一长段考");
            } finally {
                CloseUtils.close(inputStream);
            }
        }

        void exit() {
            done = true;
            CloseUtils.close(inputStream);
        }
    }

    private static class ClientWriteHandler {
        private final OutputStream outputStream;
        private final ExecutorService executorService;
        private boolean done = false;

        public ClientWriteHandler(OutputStream outputStream) {
            this.outputStream = new PrintStream(outputStream);
            this.executorService = Executors.newSingleThreadExecutor();
        }

        void send(String str) {
//            executorService.execute(() -> {
//                if (done) {
//                    return;
//                }
//                try {
//                    outputStream.write(str.getBytes());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
            executorService.execute(new WriteRunnable(str));
        }

        void exit() {
            done = true;
            CloseUtils.close(outputStream);
            executorService.shutdownNow();
        }

        class WriteRunnable implements Runnable {
            private final String msg;

            public WriteRunnable(String msg) {
                this.msg = msg;
            }

            @Override
            public void run() {
                try {
                    ClientWriteHandler.this.outputStream.write(msg.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
