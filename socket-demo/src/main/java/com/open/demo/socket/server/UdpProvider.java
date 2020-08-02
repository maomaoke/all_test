package com.open.demo.socket.server;

import com.open.demo.socket.constants.UdpConstants;
import com.open.demo.socket.utils.ByteUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * @author chenkechao
 * @date 2020/7/19 10:37 上午
 */
public class UdpProvider {

    private static Provider PROVIDER_INSTANCE;

    public static void start(int port) {
        stop();
        String sn = UUID.randomUUID().toString();
        Provider provider = new Provider(sn, port);
        provider.start();
        PROVIDER_INSTANCE = provider;
    }

    public static void stop() {
        if (PROVIDER_INSTANCE != null) {
            PROVIDER_INSTANCE.exit();
            PROVIDER_INSTANCE = null;
        }
    }

    private static class Provider extends Thread {
        private final byte[] sn;
        private final int port;
        private boolean done = false;
        private DatagramSocket ds = null;
        final byte[] buffer = new byte[128];

        public Provider(String sn, int port) {
            super();
            this.sn = sn.getBytes();
            this.port = port;
        }

        @Override
        public void run() {
            super.run();

            System.out.println("UDP Provider Started.");

            try {
                ds = new DatagramSocket(UdpConstants.PORT_SERVER);

                DatagramPacket receivePack = new DatagramPacket(buffer, buffer.length);

                while (!done) {
                    //接收
                    ds.receive(receivePack);

                    //获取发送者的ip地址
                    String clientIp = receivePack.getAddress().getHostAddress();
                    int clientPort = receivePack.getPort();
                    int clientDataLen = receivePack.getLength();
                    byte[] clientData = receivePack.getData();
                    boolean isValid = clientDataLen >= (UdpConstants.HEADER.length + 2 + 4)
                            && ByteUtils.startWith(clientData, UdpConstants.HEADER);

                    System.out.println("Server Provider receive from ip: " + clientIp + "\t port: " + clientPort +
                            "\t dataValid: " + isValid);

                    if (!isValid) {
                        continue;
                    }

                    int index = UdpConstants.HEADER.length;
                    // 解析命令
                    //验证过基础header长度后的后两位字节位决定命令, 再后四个字节决定 回送端口号
                    short cmd = (short) ((clientData[index++] << 8) | (clientData[index++] & 0xff));
                    int responsePort = (clientData[index++] << 24) | ((clientData[index++] & 0xff) << 16)
                            | ((clientData[index++] & 0xff) << 8) | (clientData[index] & 0xff);

                    if (cmd == 1 && responsePort > 0) {
                        //构建一份回送数据
                        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
                        byteBuffer.put(UdpConstants.HEADER);
                        byteBuffer.putShort((short) 2);
                        byteBuffer.putInt(port);
                        byteBuffer.put(sn);
                        int len = byteBuffer.position();

                        //直接根据发送者构建一份回送信息
                        DatagramPacket responsePacket =
                                new DatagramPacket(buffer, len, receivePack.getAddress(), responsePort);
                        ds.send(responsePacket);
                        System.out.println("ServerProvider response to: " + clientIp + "\tport: " + responsePort);
                    } else {
                        System.out.println("SeverProvider receive cmd nonsupport; cmd: " + cmd + "\t port: " + port);
                    }
                }


            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close();
            }
            System.out.println("Udp Provider Finished.");
        }

        void exit() {
            done = true;
            close();
        }

        private void close() {
            if (ds != null) {
                ds.close();
                ds = null;
            }
        }
    }
}
