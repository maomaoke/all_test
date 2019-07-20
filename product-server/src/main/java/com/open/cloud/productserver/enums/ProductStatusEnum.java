package com.open.cloud.productserver.enums;

import lombok.Getter;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-07-16-22:58
 */
@Getter
public enum ProductStatusEnum {

    UP(0, "UP", "上架"),
    DOWN(1, "DOWN", "下架");
    private int value;
    private String code;
    private String desc;

    ProductStatusEnum(int value, String code, String desc) {
        this.value = value;
        this.code = code;
        this.desc = desc;
    }}
