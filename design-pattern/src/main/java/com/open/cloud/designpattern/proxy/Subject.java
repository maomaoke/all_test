package com.open.cloud.designpattern.proxy;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-09-15-4:32 下午
 * @description Subject类,定义了RealSubject和Proxy的共用接口,这样就在任何使用RealSubject的地方都可以使用Proxy
 */
public interface Subject {
    void request();
}
