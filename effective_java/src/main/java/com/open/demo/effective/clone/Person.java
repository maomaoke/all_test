package com.open.demo.effective.clone;

/**
 * @author chenkechao
 * @date 2019/12/22 11:00 上午
 */
public class Person implements Cloneable {
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
