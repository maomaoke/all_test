package com.open.cloud.designpattern.observer;

import java.util.Random;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-09-17-9:18 下午
 */
public class RandomNumberGenerator extends NumberGenerator {

    private Random random = new Random();
    private int number = 0;
    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public void execute() {
        number = random.nextInt();
        notifyObservers();
    }
}
