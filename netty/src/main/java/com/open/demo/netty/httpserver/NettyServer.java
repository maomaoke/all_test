package com.open.demo.netty.httpserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    private int port;

    public NettyServer(int port) {
        this.port = port;
    }

    public void start() {
        EventLoopGroup bossEventGroup = new NioEventLoopGroup();
        EventLoopGroup workerEventGroup = new NioEventLoopGroup();


        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap
                .group(bossEventGroup, workerEventGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new HttpNettyChannelHandler())
                .childHandler(new HttpChannelInitializer());

        try {
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossEventGroup.shutdownGracefully();
            workerEventGroup.shutdownGracefully();
        }
    }

}
