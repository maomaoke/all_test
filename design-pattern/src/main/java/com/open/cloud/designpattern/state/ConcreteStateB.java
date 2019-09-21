package com.open.cloud.designpattern.state;

/**
 * @author chenkechao
 * @date 2019/9/19 10:29 下午
 */
public class ConcreteStateB extends State {
    @Override
    public void handle(Context context) {
        context.setState(new ConcreteStateA());
    }
}
