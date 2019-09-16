package com.open.cloud.redis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-09-16-11:44 下午
 */
@RedisHash
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    private String id;

    private String name;

    private Integer grade;
}
