package com.open.cloud.designpattern.abstractfactory.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenkechao
 * @date 2019/9/18 11:16 下午
 */
public abstract class Tray extends Item {
    protected List<Item> tray = new ArrayList<>();

    public Tray(String caption) {
        super(caption);
    }

    public void add(Item item) {
        tray.add(item);
    }
}
