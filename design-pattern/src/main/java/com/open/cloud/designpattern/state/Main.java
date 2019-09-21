package com.open.cloud.designpattern.state;

/**
 * @author chenkechao
 * @date 2019/9/19 10:30 下午
 */
public class Main {
    public static void main(String[] args) {
        Context context = new Context(new ConcreteStateA());
        context.request();
        context.request();
        context.request();
        context.request();


    }
}
