package com.open.cloud.designpattern.singleton;

/**
 * @author chenkechao
 * @date 2020/1/3 8:42 下午
 */
public class SingletonTest {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.open.cloud.designpattern.singleton.StaticInnerClassSingleton");
        StaticInnerClassSingleton singleton = StaticInnerClassSingleton.getInstance();
        System.out.println(singleton);

    }
}
