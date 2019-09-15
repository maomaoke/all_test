package com.open.cloud.designpattern.templatemethod;

/**
 * @author chenkechao
 * @date 2019/9/15 6:57 下午
 */
public abstract class AbstractDisplay {

    public void display() {
        open();
        for (int i = 0; i < 5; i++) {
            print();
        }
        close();
    }

    public abstract void open();

    public abstract void close();

    public abstract void print();
}
