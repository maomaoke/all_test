package com.open.demo.validation.pojo.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-10-17-4:20 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HelloParam {

    @NotBlank
    private String name;

    @Positive(message = "page字段必须为正数")
    @NotNull(message = "page字段不能为空")
    private Integer page;
}
