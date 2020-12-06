package com.open.cloud.designpattern.command;

/**
 * @author chenkechao
 * @date 2020/10/14 11:25 上午
 */
public class TestClient {
    public static void main(String[] args) {

        Receiver receiver = new Receiver();
        ConcreteCommand command = new ConcreteCommand(receiver);

        Invoker invoker = new Invoker();
        invoker.setCommand(command);

        invoker.invoke();
    }
}
