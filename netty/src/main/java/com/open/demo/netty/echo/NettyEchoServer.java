package com.open.demo.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chenkechao
 * @date 2019/12/14 6:18 下午
 */
@Slf4j
public class NettyEchoServer {

    private final int serverPort;
    ServerBootstrap b = new ServerBootstrap();

    public NettyEchoServer(int serverPort) {
        this.serverPort = serverPort;
    }

    public void runServer() {
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            b.group(boss, worker);
            //设置channel类型
            b.channel(NioServerSocketChannel.class);
            //设置监听端口
            b.localAddress(serverPort);
            //设置通道参数
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            b.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            //装配子通道流水间
            b.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(NettyEchoServerHandler.INSTANCE);
                }
            });

            //
            ChannelFuture channelFuture = b.bind().sync();
            log.info(" 服务器启动成功，监听端口: " +
                    channelFuture.channel().localAddress());

            //服务监听通道会一直等待通道关闭的异步任务结束
            ChannelFuture closeFuture = channelFuture.channel().closeFuture();
            closeFuture.sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyEchoServer(9999).runServer();
    }
}

