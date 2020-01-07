package com.open.demo.netty.im.codec;

import com.open.demo.netty.im.exception.InvalidFrameException;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author chenkechao
 * @date 2019/12/28 11:20 上午
 */
@Slf4j
public class ProtobufDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //标记一下当前的读指针readIndex的位置
        in.markReaderIndex();
        // 判断包头长度
        if (in.readableBytes() < 8) {
            // 不够包头
            return;
        }
        //读取魔术字
        short magic = in.readShort();
        if (magic != ProtoInstant.MAGIC_CODE) {
            String error = "客户端口令不对:" + ctx.channel().remoteAddress();
            log.error(error);
            throw new InvalidFrameException(error);
        }
        //读取版本号
        short version = in.readShort();
        //读取消息长度
        int length = in.readInt();
        // 长度如果小于0
        if (length < 0) {
            // 非法数据，关闭连接
            ctx.close();
        }
        // 读到的消息体长度如果小于传送过来的消息长度
        if (length > in.readableBytes()) {
            // 重置读取位置
            in.resetReaderIndex();
            return;
        }
    }
}
