package com.open.demo.netty.im.codec;

import com.open.demo.netty.protocol.MsgProtos;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chenkechao
 * @date 2019/12/28 10:54 上午
 */
@Slf4j
public class ProtobufEncoder extends MessageToByteEncoder<MsgProtos.Msg> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MsgProtos.Msg msg, ByteBuf out) throws Exception {
        //魔术字
        out.writeShort(ProtoInstant.MAGIC_CODE);
        //版本号
        out.writeShort(ProtoInstant.VERSION_CODE);

        byte[] bytes = msg.toByteArray();
        int length = bytes.length;
        // 将消息长度写入, 也就是消息头(Head-Content协议),这里使用4个字节
        out.writeInt(length);
        //写入消息体
        out.writeBytes(bytes);
    }
}
