package com.open.demo.netty.protocol;

import com.open.common.utils.JsonUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-12-26-11:09 上午
 */
@Slf4j
public class JsonSendClient {

    private String serverAddress;

    private int port;

    private Bootstrap bootstrap = new Bootstrap();

    public JsonSendClient(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public void runClient() {
        //1.创建反应器线程组
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(1);
        try {
            //设置反应器线程组
            bootstrap.group(eventLoopGroup);
            //设置NIO类型通道
            bootstrap.channel(NioSocketChannel.class);
            //设置监听端口
            bootstrap.remoteAddress(serverAddress, port);
            //设置通道的参数
            bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            //装配通道流水线
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new LengthFieldPrepender(4));
                    pipeline.addLast(new StringEncoder(StandardCharsets.UTF_8));
                }
            });
            ChannelFuture channelFuture = bootstrap.connect();
            channelFuture.addListener((ChannelFuture fl) -> {
              if (fl.isSuccess()) {
                  log.info("EchoClient 客户端连接成功!");
              } else {
                  log.info("EchoClient 客户端连接失败");
              }
            });

            //堵塞 直到连接完成
            channelFuture.sync();

            Channel channel = channelFuture.channel();
            for (int i = 0; i < 1000; i++) {
                JsonMessage jsonMessage = build(i, i + "->" + "疯狂的Netty");
                String json = JsonUtils.obj2Json(jsonMessage);
                channel.writeAndFlush(json);
                log.info("发送报文: {}", json);
            }
            channel.flush();

            //发送完成关闭channel
            ChannelFuture closeFuture = channel.closeFuture();
            //堵塞 直到通道关闭
            channelFuture.sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    private JsonMessage build(int code, String content) {
        return new JsonMessage(code, content);
    }

    public static void main(String[] args) {
        new JsonSendClient("127.0.0.1", 9999).runClient();
    }
}
