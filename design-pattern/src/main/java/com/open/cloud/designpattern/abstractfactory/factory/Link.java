package com.open.cloud.designpattern.abstractfactory.factory;

/**
 * @author chenkechao
 * @date 2019/9/18 11:12 下午
 */
public abstract class Link extends Item {
    protected String url;

    public Link(String caption, String url) {
        super(caption);
        this.url = url;
    }

}
