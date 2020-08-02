package com.open.demo.netty.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author chenkechao
 * @date 2020/7/12 10:43 下午
 */
public class UdpProvider {

    public static void main(String[] args) throws IOException {

        DatagramSocket ds = new DatagramSocket(2000);

        final byte[] buf = new byte[512];
        DatagramPacket receivePack = new DatagramPacket(buf, buf.length);

        ds.receive(receivePack);

        String ip = receivePack.getAddress().getHostAddress();
        int port = receivePack.getPort();
        int dataLength = receivePack.getLength();
        String data = new String(receivePack.getData(), 0, dataLength);
        System.out.println("UdpProvider receive from ip: " + ip + "port: " + port + "data: " + data);

        String responseData = "Receive data with length: " + dataLength;
        byte[] responseDataBytes = responseData.getBytes();

        DatagramPacket responsePacket =
                new DatagramPacket(responseDataBytes,
                        responseDataBytes.length,
                        receivePack.getAddress(),
                        receivePack.getPort());

    }
}
