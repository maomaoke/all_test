package com.open.cloud.designpattern.factorymethod;

/**
 * @author chenkechao
 * @date 2020/1/28 12:53 下午
 */
public class ConcreteCreator implements Creator {
    @Override
    public <T extends Product> T factory(Class<T> c) {
        Product product = null;
        try {
            product = (Product) Class.forName(c.getName()).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T) product;
    }
}
