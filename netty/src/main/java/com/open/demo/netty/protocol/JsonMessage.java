package com.open.demo.netty.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-12-25-12:21 下午
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JsonMessage {

    private Integer code;

    private String content;
}
