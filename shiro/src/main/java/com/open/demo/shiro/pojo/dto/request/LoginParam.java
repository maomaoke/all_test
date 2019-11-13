package com.open.demo.shiro.pojo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-11-13-4:26 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginParam {

    private String username;

    private String password;
}
