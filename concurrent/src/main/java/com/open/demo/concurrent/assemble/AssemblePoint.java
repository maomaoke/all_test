package com.open.demo.concurrent.assemble;

/**
 * @author chenkechao
 * @date 2020/11/20 5:32 下午
 */
public class AssemblePoint {

    private int n;

    public AssemblePoint(int n) {
        this.n = n;
    }

    public synchronized void await() throws InterruptedException {
        if (n > 0) {
            n--;
            if (n == 0) {
                notifyAll();
            } else {
                while (n != 0) {
                    wait();
                }
            }
        }
    }
}
