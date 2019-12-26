package com.open.demo.netty.protocol;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chenkechao
 * @date 2019/12/26 10:22 下午
 */
@Slf4j
public class ProtoBufServer {

    private int port;

    private ServerBootstrap serverBootstrap = new ServerBootstrap();

    public ProtoBufServer(int port) {
        this.port = port;
    }

    public void runServer() {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup();

        try {
            serverBootstrap.group(boss, worker);
            serverBootstrap.channel(NioServerSocketChannel.class);
            //设置监听端口
            serverBootstrap.localAddress(port);
            //设置通道的参数
            serverBootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            serverBootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new ProtobufVarint32FrameDecoder());
                    pipeline.addLast(new ProtobufDecoder(MsgProtos.Msg.getDefaultInstance()));
                    pipeline.addLast(new ProtobufBusinessDecoder());
                }
            });

            ChannelFuture bindFuture = serverBootstrap.bind(port);
            bindFuture.addListener((ChannelFuture cf) -> {
               if (cf.isSuccess()) {
                   log.info("服务端启动成功");
               } else {
                   log.info("服务端启动失败");
               }
            });
            bindFuture.sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }

    public static void main(String[] args) {
        new ProtoBufServer(9999).runServer();
    }
}
