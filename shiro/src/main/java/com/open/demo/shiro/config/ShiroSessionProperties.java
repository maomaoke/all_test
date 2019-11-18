package com.open.demo.shiro.config;

import lombok.Data;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-11-14-5:51 下午
 */
@Data
public class ShiroSessionProperties {

    private String cookieName = "JSESSIONID";

    private String prefix;
    /**
     * second
     */
    private Long expire = 300L;

    /**
     * 提出后重定向到的地址
     */
    private String kickOutUrl;

    /**
     * 提出 之前/之后 登录的用户, 默认提出之前登录的用户
     */
    private Boolean kickOutAfter = false;

    /**
     * 同时在线人数
     */
    private Integer maxSession = 1;
}
