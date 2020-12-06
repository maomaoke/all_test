package com.open.demo.netty.flash.netty;

import com.open.demo.netty.LoginUtil;
import com.open.demo.netty.flash.netty.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author chenkechao
 * @date 2020/10/1 10:40 上午
 */
public class NettyClient {

    private static final int MAX_RETRY = 5;

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new ClientHandler());
                    }
                });

//        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8000)
//                .addListener(future -> {
//                    if (future.isSuccess()) {
//                        connect(bootstrap, );
//                        System.out.println("连接成功");
//                    } else {
//                        System.err.println("连接失败");
//                    }
//                }).sync();
//
//        Channel channel = channelFuture.channel();
//
//        while (true) {
//            channel.writeAndFlush("Hello World ! " + new Date());
//            Thread.sleep(2000);
//        }
        connect(bootstrap, "127.0.0.1", 8000, 5);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port)
                .addListener(future -> {
                    if (future.isSuccess()) {
                        startConsoleThread(((ChannelFuture) future).channel());
                        System.out.println("连接成功");
                    } else if (retry == 0) {
                        System.err.println("重试次数已用完, 放弃连接!");
                    } else {
                        // 第几次重连
                        int order = (MAX_RETRY - retry) + 1;
                        // 本次重连的间隔
                        int delay = 1 << order;
                        System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                        bootstrap.config().group()
                                .schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
                    }
                });
    }

    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (LoginUtil.hasLogin(channel)) {
                    System.out.println("输入消息发送至服务端: ");
                    Scanner sc = new Scanner(System.in);
                    String line = sc.nextLine();

                    MessageRequestPacket packet = new MessageRequestPacket();
                    packet.setMessage(line);
                    ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(channel.alloc(), packet);
                    channel.writeAndFlush(byteBuf);
                }
            }
        }).start();
    }
}
