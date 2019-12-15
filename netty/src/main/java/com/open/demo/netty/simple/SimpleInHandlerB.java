package com.open.demo.netty.simple;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chenkechao
 * @date 2019/12/4 9:58 下午
 */
@Slf4j
public class SimpleInHandlerB extends ChannelInboundHandlerAdapter {
    private static final String B = "入站处理器B";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info(B + "channelActive方法被调用");
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info(B + ":channelRead方法被调用");
        super.channelRead(ctx, msg);
    }
}
