package com.open.demo.netty.flash.netty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import static com.open.demo.netty.flash.netty.Command.LOGIN_REQUEST;

/**
 * @author chenkechao
 * @date 2020/10/2 1:01 下午
 */
@Data
public class LoginRequestPacket extends Packet {
    private String userId;

    private String username;

    private String password;

    @JsonIgnore
    @Override
    public Byte getCommand() {

        return LOGIN_REQUEST;
    }
}
