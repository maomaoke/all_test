package com.open.demo.bean.pojo;

import javax.annotation.PostConstruct;

/**
 * todo description
 *
 * @author CHEN-KE-CHAO
 * @date 2020-02-19-9:24 下午
 */
public class Man extends User {

    @PostConstruct
    public void init() {
        System.out.println("初始化方法");
    }
}
