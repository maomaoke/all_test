package com.open.cloud.designpattern.observer;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-09-17-9:20 下午
 */
public class DigitObserver implements Observer {
    @Override
    public void update(NumberGenerator generator) {
        System.out.println(generator.getNumber());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
