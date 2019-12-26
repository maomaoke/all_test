package com.open.demo.netty.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chenkechao
 * @date 2019/12/26 11:29 下午
 */
@Slf4j
public class ProtobufBusinessDecoder extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MsgProtos.Msg message = (MsgProtos.Msg) msg;
        log.info("收到一个MsgProtos.Msg数据包");
        log.info("protoMsg.getId(): = {}", message.getId());
        log.info("protoMsg.getContent(): = {}", message.getContent());
    }
}
