package com.open.demo.netty.protocol;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-12-23-8:31 下午
 */
public class JsonServer {

    private ServerBootstrap b = new ServerBootstrap();

    private int serverPort;

    public void runServer() {
        //创建反应器线程组
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup work = new NioEventLoopGroup();

        //设置反应器线程组
        b.group(boss, work);
        //设置NIO类型通道
        b.channel(NioServerSocketChannel.class);
        //设置监听端口
        b.localAddress(serverPort);
        //设置通道的参数
        b.option(ChannelOption.SO_KEEPALIVE, true);
        b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

        //装配子通道流水线
        b.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4));
                pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                pipeline.addLast(new JsonMsgDecoder());
            }
        });

        //开始绑定服务器
//        b.bind().sync();
    }

    public static void main(String[] args) {
        int i = ~ 5;
        System.out.println(i);
    }
}
