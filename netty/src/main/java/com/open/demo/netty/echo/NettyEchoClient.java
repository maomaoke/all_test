package com.open.demo.netty.echo;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Scanner;

/**
 * @author chenkechao
 * @date 2019/12/14 6:56 下午
 */
@Slf4j
public class NettyEchoClient {

    private int serverPort;
    private String serverIp;
    Bootstrap b = new Bootstrap();

    public NettyEchoClient(String serverIp, int serverPort) {
        this.serverPort = serverPort;
        this.serverIp = serverIp;
    }

    public void runClient() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            //1.设置反应器 线程组
            b.group(eventLoopGroup);
            //2.设置nio类型的通道
            b.channel(NioSocketChannel.class);
            //3.设置监听端口
            b.remoteAddress(serverIp, serverPort);
            //4.设置byte buf参数
            b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            //5.装配子通道流水线
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(NettyEchoClientHandler.INSTANCE);
                }
            });

            ChannelFuture channelFuture = b.connect();
            channelFuture.addListener((ChannelFuture cf) -> {
               if (cf.isSuccess()) {
                   log.info("EchoClient客户端连接成功");
               } else {
                   log.info("EchoClient客户端连接失败");
               }
            });

            //阻塞 直到连接成功
            channelFuture.sync();
            Channel channel = channelFuture.channel();
            Scanner scanner = new Scanner(System.in);
            log.info("请输入发送内容: ");
            while (scanner.hasNext()) {
                String next = scanner.next();
                byte[] bytes = (new Date().toString() + ">>" + next).getBytes(StandardCharsets.UTF_8);
                ByteBuf buffer = channel.alloc().buffer();
                buffer.writeBytes(bytes);
                channel.writeAndFlush(buffer);
                log.info("请输入发送内容");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyEchoClient("0:0:0:0:0:0:0:0", 9999).runClient();
    }
}
