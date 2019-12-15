package com.open.demo.netty.decode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author chenkechao
 * @date 2019/12/15 1:43 下午
 */
@Slf4j
public class StringReplayDecoder extends ReplayingDecoder<StringReplayDecoder.Status> {

    enum Status {
        /**
         * 第一阶段,解析出入的字符串长度
         */
        PARSE_1,
        /**
         * 第二阶段,读取字符串内容
         */
        PARSE_2;
    }

    private int length;
    private byte[] inBytes;

    public StringReplayDecoder() {
        super(Status.PARSE_1);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        switch (state()) {
            case PARSE_1:
                length = in.readInt();
                inBytes = new byte[length];
                checkpoint(Status.PARSE_2);
                break;
            case PARSE_2:
                in.readBytes(inBytes, 0, length);
                String content = new String(inBytes, StandardCharsets.UTF_8);
                log.info("content {}", content);
                out.add(content);
                break;
            default:
                break;
        }
    }
}
