package com.open.demo.concurrent;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenkechao
 * @date 2020/1/23 7:22 下午
 */
public class ReentrantLockTest {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        System.out.println("开始获取锁" + System.currentTimeMillis());

        new Thread(() -> {
            lock.lock();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                System.out.println("哈哈");
            } finally {
                lock.unlock();
            }

        }).start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                try {
                    System.out.println("获取锁后" + System.currentTimeMillis());
                } finally {
                    System.out.println("结束");
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
