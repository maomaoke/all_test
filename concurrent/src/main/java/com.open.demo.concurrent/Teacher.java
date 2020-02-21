package com.open.demo.concurrent;

import java.util.List;

/**
 * @author chenkechao
 * @date 2020/1/7 9:44 下午
 */
public class Teacher {

    private final static int MAX = 10;

    private Thread thread;

    private List tasks;

    public Teacher(Runnable runnable, List tasks) {
        assert tasks != null;
        this.thread = new Thread(runnable);
        this.tasks = tasks;
    }

    public void start() {
        this.thread.start();
    }

    private void arrangePunishment() {
        while (true) {
            synchronized (tasks) {
                if (tasks.size() < MAX) {

                }
            }
        }
    }

    private static class Work implements Runnable {
        @Override
        public void run() {

        }
    }
}
