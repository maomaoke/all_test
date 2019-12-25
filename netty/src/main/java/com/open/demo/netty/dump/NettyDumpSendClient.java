package com.open.demo.netty.dump;

import com.open.demo.netty.echo.NettyEchoClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-12-21-1:22 下午
 */
@Slf4j
public class NettyDumpSendClient {
    private String serverIp;

    private int serverPort;

    private Bootstrap bootstrap = new Bootstrap();

    public NettyDumpSendClient(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    public void runClient() {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup(1);

        try {
            //1. 设置反应器 线程组
            bootstrap.group(eventLoopGroup);
            //2. 设置nio类型通道
            bootstrap.channel(NioSocketChannel.class);
            //3. 设置监听端口
            bootstrap.remoteAddress(serverIp, serverPort);
            //4. 设置byte buf参数
            bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            //5.装配子通道流水线
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(NettyEchoClientHandler.INSTANCE);
                }
            });

            ChannelFuture channelFuture = bootstrap.connect();
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
            //发送大量的文字
            String content = "疯狂创客圈: 高性能学习者社区!";
            byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
            for (int i = 0; i < 1000; i++) {
                ByteBuf buffer = channel.alloc().buffer();
                buffer.writeBytes(bytes);
                channel.writeAndFlush(buffer);
                log.info("第{}次发送", i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyDumpSendClient("127.0.0.1", 9999).runClient();


    }

}
