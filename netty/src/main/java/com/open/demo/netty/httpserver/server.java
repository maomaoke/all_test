package com.open.demo.netty.httpserver;

public class server {
    public static void main(String[] args) {
        new NettyServer(8001).start();
    }
}
