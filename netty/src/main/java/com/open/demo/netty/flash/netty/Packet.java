package com.open.demo.netty.flash.netty;

import lombok.Data;

/**
 * @author chenkechao
 * @date 2020/10/2 12:59 下午
 */
@Data
public abstract class Packet {

    private Byte version = 1;

    public abstract Byte getCommand();
}
