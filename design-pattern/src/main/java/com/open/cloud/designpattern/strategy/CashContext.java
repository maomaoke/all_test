package com.open.cloud.designpattern.strategy;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-09-14-10:16 上午
 */
public class CashContext {

    private CashSuper cs;

    public CashContext(String type) {
        switch (type) {
            case "正常收费":
                cs = new CashNormal();
                break;
        }
    }

    public double getResult(double money) {
        return this.cs.acceptCash(money);
    }
}
