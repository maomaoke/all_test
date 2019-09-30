package com.open.cloud.designpattern.abstractfactory.factory;

/**
 * @author chenkechao
 * @date 2019/9/18 11:11 下午
 */
public abstract class Item {

    protected String caption;

    public Item(String caption) {
        this.caption = caption;
    }

    public abstract String makeHTML();
}
