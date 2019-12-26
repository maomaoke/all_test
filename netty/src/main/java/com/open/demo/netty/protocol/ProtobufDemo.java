package com.open.demo.netty.protocol;

/**
 * @author chenkechao
 * @date 2019/12/26 9:44 下午
 */
public class ProtobufDemo {
    public static void main(String[] args) {
        MsgProtos.Msg.Builder builder = MsgProtos.Msg.newBuilder();
        builder.setId(1000);
        builder.setContent("");
        MsgProtos.Msg msg = builder.build();
    }
}
