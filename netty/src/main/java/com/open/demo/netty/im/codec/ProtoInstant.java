package com.open.demo.netty.im.codec;

/**
 * @author chenkechao
 * @date 2019/12/28 10:59 上午
 */
public class ProtoInstant {

    /**
     * 魔数，可以通过配置获取
     */
    public static final short MAGIC_CODE = 0x86;

    /**
     * 版本号
     */
    public static final short VERSION_CODE = 0x01;
}
