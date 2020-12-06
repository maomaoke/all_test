package com.open.cloud.designpattern.command;

/**
 * @author chenkechao
 * @date 2020/10/14 11:27 上午
 */
public class ConcreteCommand implements Command {

    private final Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action();
    }
}
