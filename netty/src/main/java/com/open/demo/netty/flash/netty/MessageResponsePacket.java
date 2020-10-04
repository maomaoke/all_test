package com.open.demo.netty.flash.netty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import static com.open.demo.netty.flash.netty.Command.MESSAGE_RESPONSE;

/**
 * @author chenkechao
 * @date 2020/10/4 12:07 下午
 */
@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @JsonIgnore
    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
