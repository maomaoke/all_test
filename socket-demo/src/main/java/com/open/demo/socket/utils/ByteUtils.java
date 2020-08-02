package com.open.demo.socket.utils;

/**
 * @author chenkechao
 * @date 2020/7/19 11:34 上午
 */
public class ByteUtils {

    public static boolean startWith(byte[] a, byte[] b) {
        assert a != null && a.length > 0;
        assert b != null && a.length > b.length;

        for (int i = 0; i < b.length; i++) {
            if (b[i] != a[i]) {
                return false;
            }
        }
        return true;
    }
}
