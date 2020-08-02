package com.open.demo.socket.client.bean;

/**
 * @author chenkechao
 * @date 2020/7/19 12:09 下午
 */
public class ServerInfo {

    private String sn;
    private int port;
    private String address;

    public ServerInfo(int port, String address, String sn) {
        this.sn = sn;
        this.port = port;
        this.address = address;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ServerInfo{" +
                "sn='" + sn + '\'' +
                ", port=" + port +
                ", address='" + address + '\'' +
                '}';
    }
}
