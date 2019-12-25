package com.open.demo.concurrent;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-12-23-7:24 下午
 */
public class ThreadDemo {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            System.out.println("异步线程" + Thread.currentThread().getName());
        });
        thread.setName("线程1");

        thread.start();

        Thread.sleep(1000);

        thread.start();
    }
}
