package com.open.demo.concurrent.future;

import java.util.concurrent.Callable;

/**
 * @author chenkechao
 * @date 2020/11/20 5:05 下午
 */
public class MyExecutor {

    public <V> MyFuture<V> execute(final Callable<V> task) {
        final Object lock = new Object();
        final ExecuteThread<V> thread = new ExecuteThread<>(task, lock);
        thread.start();

        return () -> {
            synchronized (lock) {
                while (!thread.isDone()) {
                    lock.wait();
                }
                if (thread.getException() != null) {
                    throw thread.getException();
                }
                return thread.getResult();
            }
        };
    }

    public static void main(String[] args) {
        MyExecutor executor = new MyExecutor();
        Callable<Integer> subTask = () -> {
            int millis = (int) (Math.random() * 1000);
            Thread.sleep(millis);
            return millis;
        };

        MyFuture<Integer> future = executor.execute(subTask);

        Integer result = null;
        try {
            result = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }
}
