package com.open.demo.netty;

import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @author chenkechao
 * @date 2020/10/4 12:09 下午
 */
public class LoginUtil {
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);

        return loginAttr.get() != null;
    }
}
