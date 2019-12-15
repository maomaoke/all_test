package com.open.demo.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * @author chenkechao
 * @date 2019/12/14 6:28 下午
 */
@Slf4j
@ChannelHandler.Sharable
public class NettyEchoServerHandler extends ChannelInboundHandlerAdapter {

    private NettyEchoServerHandler() {
    }

    public final static NettyEchoServerHandler INSTANCE = new NettyEchoServerHandler();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        log.info("nsg type: " + (in.hasArray() ? "堆内存": "直接内存"));
        int len = in.readableBytes();
        byte[] arr = new byte[len];
        in.getBytes(0, arr);
        log.info("server received: " + new String(arr, StandardCharsets.UTF_8));
        log.info("写回前,引用计数 msg.refCnt: " + in.refCnt());
        ChannelFuture channelFuture = ctx.writeAndFlush(msg);
        channelFuture.addListener((ChannelFutureListener) -> {
           log.info("写会后, 引用计数, msg.refCnt: " + in.refCnt());
        });
    }
}
