package com.open.demo.netty.flash.netty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import static com.open.demo.netty.flash.netty.Command.LOGIN_RESPONSE;

/**
 * @author chenkechao
 * @date 2020/10/2 5:09 下午
 */
@Data
public class LoginResponsePacket extends Packet {

    private Boolean success;

    private String reason;

    @JsonIgnore
    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
