package com.open.demo.netty.protocol;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import jdk.nashorn.internal.runtime.ECMAErrors;
import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

/**
 * @author chenkechao
 * @date 2019/12/27 10:17 下午
 */
@Slf4j
public class ProtoBufSendClient {

    private String serverAddress;

    private int port;

    private Bootstrap bootstrap = new Bootstrap();

    public ProtoBufSendClient(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public void runClient() {
        //创建反应器线程组
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(1);
        try {
            //设置反应器线程组
            bootstrap.group(eventLoopGroup);
            //设置NIO类型通道
            bootstrap.channel(NioSocketChannel.class);
            //设置通道的参数
            bootstrap.option(ChannelOption.ALLOCATOR, ByteBufAllocator.DEFAULT);
            //设置监听端口
            bootstrap.remoteAddress(serverAddress, port);

            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                    pipeline.addLast(new ProtobufEncoder());
                }
            });
            
            //连接服务器
            ChannelFuture connectFuture = bootstrap.connect();
            //堵塞直到连接成功
            connectFuture.sync();
            Channel channel = connectFuture.channel();

            for (int i = 0; i < 1000; i++) {
                String content = i + "->" + "我在学Netty";
                MsgProtos.Msg msg = build(i, content);
                channel.writeAndFlush(msg);
                log.info("发送报文树: {}", i);
            }
            channel.flush();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    private MsgProtos.Msg build(int id, String content) {
        MsgProtos.Msg.Builder builder = MsgProtos.Msg.newBuilder();
        builder.setId(id);
        builder.setContent(content);
        return builder.build();
    }

    public static void main(String[] args) {
        new ProtoBufSendClient("127.0.0.1", 9999).runClient();
    }
}
