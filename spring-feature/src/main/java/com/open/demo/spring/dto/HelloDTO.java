package com.open.demo.spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-12-19-7:34 下午
 */
@Data
public class HelloDTO {

    @ApiModelProperty(example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

}
