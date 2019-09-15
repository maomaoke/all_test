package com.open.cloud.designpattern.strategy;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-09-14-10:19 上午
 */
public class CashRebate implements CashSuper {
    @Override
    public double acceptCash(double money) {
        return 0;
    }
}
