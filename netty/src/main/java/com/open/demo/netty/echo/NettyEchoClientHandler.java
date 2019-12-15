package com.open.demo.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * @author chenkechao
 * @date 2019/12/14 8:53 下午
 */
@Slf4j
public class NettyEchoClientHandler extends ChannelInboundHandlerAdapter {
    private NettyEchoClientHandler() {
    }
    public final static NettyEchoClientHandler INSTANCE = new NettyEchoClientHandler();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf) msg;
        int len = byteBuf.readableBytes();
        byte[] arr = new byte[len];
        byteBuf.getBytes(0, arr);
        log.info("client received: " + new String(arr, StandardCharsets.UTF_8));

        super.channelRead(ctx, msg);
    }
}
