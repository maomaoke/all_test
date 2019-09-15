package com.open.cloud.designpattern.strategy;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-09-14-10:20 上午
 */
public class CashReturn implements CashSuper {
    @Override
    public double acceptCash(double money) {
        return 0;
    }
}
