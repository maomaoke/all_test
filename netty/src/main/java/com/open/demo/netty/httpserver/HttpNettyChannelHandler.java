package com.open.demo.netty.httpserver;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

public class HttpNettyChannelHandler implements ChannelHandler {
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server bound success");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server removed success");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("there throws exception when server binding ");
    }
}
