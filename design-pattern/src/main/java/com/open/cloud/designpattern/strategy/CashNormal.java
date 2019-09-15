package com.open.cloud.designpattern.strategy;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-09-14-10:18 上午
 */
public class CashNormal implements CashSuper {
    @Override
    public double acceptCash(double money) {
        return 0;
    }
}
