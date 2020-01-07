package com.open.demo.spring;

/**
 * @author chenkechao
 * @date 2020/1/7 8:57 下午
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        Thread xiaopang = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("I'm doing my work");
                try {
                    System.out.println("I will sleep");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("My sleeping was interrupted");
                }
                System.out.println("I'm interrupted?" + Thread.currentThread().isInterrupted());
            }
        });
        xiaopang.start();
        Thread.sleep(1);
        xiaopang.interrupt();
    }
}
