package com.open.cloud.designpattern.decorator;

/**
 * @author chenkechao
 * @date 2020/1/29 4:35 下午
 */
public abstract class CarDecorator implements Car {

    private Car car;

    public CarDecorator(Car car) {
        this.car = car;
    }

    @Override
    public void show() {
        this.car.show();
    }
}
