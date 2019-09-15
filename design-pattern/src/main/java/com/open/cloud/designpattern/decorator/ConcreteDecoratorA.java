package com.open.cloud.designpattern.decorator;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-09-15-4:15 下午
 */
public class ConcreteDecoratorA extends Decorator {

    private String addedState;

    @Override
    public void operation() {
        super.operation();
        addedState = "";
        System.out.println("具体装饰A的操作");
    }


}
