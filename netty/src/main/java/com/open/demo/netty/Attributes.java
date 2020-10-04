package com.open.demo.netty;

import io.netty.util.AttributeKey;

/**
 * @author chenkechao
 * @date 2020/10/4 12:09 下午
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

}
