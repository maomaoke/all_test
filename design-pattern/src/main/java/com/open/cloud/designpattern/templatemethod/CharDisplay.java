package com.open.cloud.designpattern.templatemethod;

/**
 * @author chenkechao
 * @date 2019/9/15 7:00 下午
 */
public class CharDisplay extends AbstractDisplay {

    private char ch;

    public CharDisplay(char ch) {
        this.ch = ch;
    }

    @Override
    public void open() {
        System.out.println("<<");
    }

    @Override
    public void close() {
        System.out.println(">>");
    }

    @Override
    public void print() {
        System.out.println(ch);
    }
}
