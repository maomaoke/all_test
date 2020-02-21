package com.open.cloud.designpattern.factorymethod;

/**
 * @author chenkechao
 * @date 2020/1/28 12:51 下午
 */
public interface Creator {
    <T extends Product> T factory(Class<T> c);
}
