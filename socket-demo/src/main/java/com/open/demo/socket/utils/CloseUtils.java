package com.open.demo.socket.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author chenkechao
 * @date 2020/7/26 7:06 下午
 */
public class CloseUtils {
    public static void close(Closeable... closeable) {
        if (closeable == null) {
            return;
        }

        for (Closeable c : closeable) {
            try {
                c.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
