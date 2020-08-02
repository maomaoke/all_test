package com.open.demo.netty.tcp;

import java.net.Socket;
import java.net.SocketException;

/**
 * @author chenkechao
 * @date 2020/7/16 8:43 上午
 */
public class Client {
    public static void main(String[] args) {

    }

    private static void initSocket(Socket socket) throws SocketException {

        socket.setTcpNoDelay(true);

        // 是否需要在长时间无数据响应时发送确认数据(类似心跳包), 时间大约2小时
        socket.setKeepAlive(true);

        /*
            对于close关闭操作行为 进行怎样的处理; 默认false, 0
            false, 0: 默认参数, 关闭时立即返回, 底层系统接管输出流, 将缓冲区内的数据发送完成
            true, 0: 关闭时立即返回, 缓冲区数据抛弃, 直接发送RST结束命令到对方, 并无需经过2MSL等待
            true, 20; 关闭时最长堵塞20毫秒, 随后按第二情况处理
         */
        socket.setSoLinger(true, 20);

        /*
          是否让紧急数据优先发送, 默认为false; 紧急数据通过 {@link socket.sendUrgentData(1)} 发送
         */
        socket.setOOBInline(true);

        //设置接收,发送缓冲区大小
        socket.setReceiveBufferSize(64 * 1024 * 1024);
        socket.setSendBufferSize(64 * 1024 * 1024);

        // 设置性能参数: 短链接, 延迟, 带宽的相对重要性
        socket.setPerformancePreferences(1, 1, 1);
    }
}
