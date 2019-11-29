package com.open.demo.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author chenkechao
 * @date 2019/11/18 10:10 下午
 */
public class MyExecutor {
    private final int poolSize;

    private final RunnableTaskQueue runnableTaskQueue;

    private final List<Thread> threads = new ArrayList<>();


    public MyExecutor(int poolSize) {
        this.poolSize = poolSize;
        this.runnableTaskQueue = new RunnableTaskQueue();
        Stream.iterate(1, item -> item + 1).limit(poolSize).forEach(item -> {
            initThread();
        });
    }

    private void initThread() {
        while (threads.size() <= poolSize) {
            Thread thread = new Thread(() -> {
                while (true) {
                    Runnable task = null;
                    try {
                        task = runnableTaskQueue.getTask();
                        task.run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            threads.add(thread);
            thread.start();;
        }
    }

    public void execute(Runnable runnable) {
        runnableTaskQueue.addTask(runnable);
    }
}
