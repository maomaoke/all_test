package com.open.demo.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @author chenkechao
 * @date 2019/11/18 10:27 下午
 */
public class Client {
    public static void main(String[] args) {
        MyExecutor executor = new MyExecutor(5);

        Stream.iterate(1, item-> item + 1).limit(10).forEach(item -> {
            executor.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " execute this task");
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
    }
}
