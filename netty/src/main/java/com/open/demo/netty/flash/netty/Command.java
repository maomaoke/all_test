package com.open.demo.netty.flash.netty;

/**
 * @author chenkechao
 * @date 2020/10/2 1:01 下午
 */
public interface Command {

    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;
}
