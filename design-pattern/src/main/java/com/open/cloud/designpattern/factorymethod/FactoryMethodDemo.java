package com.open.cloud.designpattern.factorymethod;

/**
 * @author chenkechao
 * @date 2020/1/28 1:08 下午
 */
public class FactoryMethodDemo {
    public static void main(String[] args) {
        Creator creator = new ConcreteCreator();
        Product product = creator.factory(ConcreteProduct.class);

    }
}
