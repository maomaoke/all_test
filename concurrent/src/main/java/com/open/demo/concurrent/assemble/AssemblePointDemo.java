package com.open.demo.concurrent.assemble;

/**
 * @author chenkechao
 * @date 2020/11/20 5:39 下午
 */
public class AssemblePointDemo {
    static class Tourist extends Thread {
        AssemblePoint ap;

        public Tourist(AssemblePoint ap) {
            this.ap = ap;
        }

        @Override
        public void run() {
            try {
                Thread.sleep((long) (Math.random() * 1000));
                ap.await();
                System.out.println("arrived");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            int num = 10;
            Tourist[] threads = new Tourist[num];
            AssemblePoint ap = new AssemblePoint(10);
            for (int i = 0; i < num; i++) {
                threads[i] = new Tourist(ap);
                threads[i].start();
            }
        }
    }
}
