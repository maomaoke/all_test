package com.open.demo.concurrent;

import java.util.LinkedList;

/**
 * @author chenkechao
 * @date 2019/11/18 10:10 下午
 */
public class RunnableTaskQueue {

    private final LinkedList<Runnable> tasks = new LinkedList<>();

    public Runnable getTask() throws InterruptedException {
        synchronized (tasks) {
            while (tasks.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " says task queue is empty. I will wait.");
                tasks.wait();
            }
            return tasks.removeFirst();
        }
    }

    public void addTask(Runnable runnable) {
        synchronized (tasks) {
            tasks.add(runnable);
            tasks.notifyAll();
        }
    }
}
