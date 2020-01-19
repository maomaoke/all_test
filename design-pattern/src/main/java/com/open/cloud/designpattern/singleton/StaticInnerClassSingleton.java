package com.open.cloud.designpattern.singleton;

/**
 * @author chenkechao
 * @date 2020/1/3 8:42 下午
 */
public class StaticInnerClassSingleton {

    static {
        System.out.println("StaticInnerClassSingleton 类 开始初始化");
    }

    private static class Inner {
        static {
            System.out.println("StaticInnerClassSingleton.Inner 类 开始初始化");
        }
        private static StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance() {
        return Inner.INSTANCE;
    }

    private StaticInnerClassSingleton() {
    }
}
