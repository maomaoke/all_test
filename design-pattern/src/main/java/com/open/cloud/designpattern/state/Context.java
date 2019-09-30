package com.open.cloud.designpattern.state;

/**
 * @author chenkechao
 * @date 2019/9/19 10:26 下午
 */
public class Context {
    private State state;

    public Context(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void request() {
        this.state.handle(this);
    }
}
