package com.open.cloud.designpattern.proxy;

import java.util.Objects;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-09-15-4:36 下午
 */
public class Proxy implements Subject {

    private Subject subject;

    public Proxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        if (Objects.nonNull(subject)) {
            subject.request();
        }
    }
}
