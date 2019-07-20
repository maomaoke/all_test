package com.open.cloud.orderserver.enums;

import lombok.Getter;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-07-19-13:26
 */
@Getter
public enum OrderStatusEnum {

    UNPAID(0, "UNPAID", "等待支付"),
    PAID(1, "PAID", "已支付"),
    CANCEL(2, "CANCEL", "取消");
    private int value;
    private String code;
    private String desc;

    OrderStatusEnum(int value, String code, String desc) {
        this.value = value;
        this.code = code;
        this.desc = desc;
    }

}
