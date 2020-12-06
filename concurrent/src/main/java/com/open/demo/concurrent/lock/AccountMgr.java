package com.open.demo.concurrent.lock;

/**
 * @author chenkechao
 * @date 2020/11/24 9:08 上午
 */
public class AccountMgr {

    public static class NoEnoughMoneyException extends Exception {

    }

    public static void transfer(Account from, Account to, double money) throws NoEnoughMoneyException {

        from.lock();
        try {
            to.lock();
            try {
                if (from.getMoney() >= money) {
                    from.add(money);
                    to.add(money);
                } else {
                    throw new NoEnoughMoneyException();
                }
            } finally {
                to.unlock();
            }
        } finally {
            from.unlock();
        }
    }
}
