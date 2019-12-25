package com.open.demo.netty.protocol;

import com.open.common.utils.JsonUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-12-23-8:42 下午
 */
@Slf4j
public class JsonMsgDecoder extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String json = (String) msg;
        JsonMessage message = JsonUtils.json2Obj(json, JsonMessage.class);
        log.info("message = {}", message);
    }
}
