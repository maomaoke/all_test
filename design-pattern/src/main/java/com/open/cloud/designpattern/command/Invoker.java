package com.open.cloud.designpattern.command;

/**
 * @author chenkechao
 * @date 2020/10/14 11:28 上午
 */
public class Invoker {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void invoke() {
        command.execute();
    }

}
