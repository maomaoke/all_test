package com.open.demo.netty.flash.netty;

import com.open.demo.netty.flash.netty.handler.ServerHandler;
import com.open.demo.netty.flash.netty.handler.in.InBoundHandlerA;
import com.open.demo.netty.flash.netty.handler.in.InBoundHandlerB;
import com.open.demo.netty.flash.netty.handler.in.InBoundHandlerC;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author chenkechao
 * @date 2020/10/1 10:37 上午
 */
public class NettyServer {

    public static void main(String[] args) {

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        serverBootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .handler(new ChannelInitializer<NioServerSocketChannel>() {
                    // server channel socket 初始化成功的回调
                    //用于指定处理新连接数据的读写处理逻辑
                    @Override
                    protected void initChannel(NioServerSocketChannel nioServerSocketChannel) throws Exception {

                    }
                })
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    // channel socket 初始化呢成功的回调
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
//                        pipeline.addLast(new StringDecoder());
//                        pipeline.addLast(new SimpleChannelInboundHandler<String>() {
//                            @Override
//                            protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
//                                System.out.println(s);
//                            }
//                        });

//                        pipeline.addLast(new InBoundHandlerA());
//                        pipeline.addLast(new InBoundHandlerB());
//                        pipeline.addLast(new InBoundHandlerC());

                        pipeline.addLast(new ServerHandler());
//
//                        pipeline.addLast(new OutBoundHandlerA());
//                        pipeline.addLast(new OutBoundHandlerB());
//                        pipeline.addLast(new OutBoundHandlerC());
                    }
                });

        bind(serverBootstrap, 8000);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port)
                .addListener(future -> {
                    if (future.isSuccess()) {
                        System.out.println("端口绑定成功");
                    } else {
                        System.err.println("端口绑定失败");
                        bind(serverBootstrap, port+1);
                    }
                });
    }
}
