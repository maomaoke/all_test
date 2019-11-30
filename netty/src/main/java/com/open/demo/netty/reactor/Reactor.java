package com.open.demo.netty.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Objects;
import java.util.Set;

/**
 * @author chenkechao
 * @date 2019/11/29 10:58 下午
 */
public class Reactor implements Runnable {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public Reactor() throws IOException {
        //获取reactor反应器
        selector = Selector.open();
        //server socket
        serverSocketChannel = ServerSocketChannel.open();
        //address
        SocketAddress address = new InetSocketAddress(9998);
        //绑定
        serverSocketChannel.bind(address);
        //设置非堵塞
        serverSocketChannel.configureBlocking(false);
        /*
           server socket 注册 反应器的tcp连接事件, tcp连接成功就会, 返回一个select注册key
           SelectionKey 代表着socket 与 selector(Reactor) 之间的绑定,如果selector接收到对应的事件,就烦返回对应socket的绑定(SelectionKey)
         */
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach(new AcceptHandler());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                int count = selector.select();
                Set<SelectionKey> selected = selector.selectedKeys();
                for (SelectionKey selectionKey : selected) {
                    dispatch(selectionKey);
                }
                selected.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dispatch(SelectionKey selectionKey) {
        Runnable handler = (Runnable) selectionKey.attachment();
        if (Objects.nonNull(handler)) {
            handler.run();
        }
    }

    class AcceptHandler implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (Objects.nonNull(socketChannel)) {
                    new IOHandler(selector, socketChannel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(new Reactor()).start();
    }
}
