package com.open.demo.bean.interfaces;

/**
 * @author chenkechao
 * @date 2020/8/15 7:16 下午
 */
public interface ProxyFactory <T> {

    T createProxy(Class<T> type);
}
