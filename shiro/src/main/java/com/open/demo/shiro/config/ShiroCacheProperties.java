package com.open.demo.shiro.config;

import lombok.Data;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-11-14-5:50 下午
 */
@Data
public class ShiroCacheProperties {

    private Long expire;

    private String prefix;
}
