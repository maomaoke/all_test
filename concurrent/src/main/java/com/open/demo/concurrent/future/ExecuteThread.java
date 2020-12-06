package com.open.demo.concurrent.future;

import java.util.concurrent.Callable;

/**
 * @author chenkechao
 * @date 2020/11/20 5:13 下午
 */
public class ExecuteThread<V> extends Thread {

    private V result = null;
    private Exception exception = null;
    private boolean done = false;
    private final Callable<V> task;
    private final Object lock;

    public ExecuteThread(Callable<V> task, Object lock) {
        this.task = task;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            result = task.call();
        } catch (Exception e) {
            exception = e;
        } finally {
            synchronized (lock) {
                done = true;
                lock.notifyAll();
            }
        }
    }

    public V getResult() {
        return result;
    }

    public Exception getException() {
        return exception;
    }

    public boolean isDone() {
        return done;
    }
}
