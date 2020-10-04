package com.open.demo.netty.flash.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author chenkechao
 * @date 2020/10/2 1:20 下午
 */
public class PacketCodeC {
    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private static final int MAGIC_NUMBER = 0x12345678;

    public ByteBuf encode(ByteBufAllocator alloc, Packet packet) {
        // 1. 创建 ByteBuf 对象
        ByteBuf byteBuf = alloc.ioBuffer();
        // 2. 序列化 Java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 3. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }


    public Packet decode(ByteBuf byteBuf) {
        // 跳过 magic number
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        if (Serializer.JSON_SERIALIZER == serializeAlgorithm) {
            return Serializer.DEFAULT;
        }
        return null;
    }

    private Class<? extends Packet> getRequestType(byte command) {
        if (Command.LOGIN_REQUEST.equals(command)) {
            return LoginRequestPacket.class;
        }
        else if (Command.LOGIN_RESPONSE.equals(command)) {
            return LoginResponsePacket.class;
        }
        else if (Command.MESSAGE_REQUEST.equals(command)) {
            return MessageRequestPacket.class;
        }
        else if (Command.MESSAGE_RESPONSE.equals(command)) {
            return MessageResponsePacket.class;
        }
        return null;
    }
}
