package com.open.demo.netty.flash.netty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import static com.open.demo.netty.flash.netty.Command.MESSAGE_REQUEST;

/**
 * @author chenkechao
 * @date 2020/10/4 12:06 下午
 */
@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @JsonIgnore
    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
