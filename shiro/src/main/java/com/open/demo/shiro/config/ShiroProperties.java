package com.open.demo.shiro.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-11-14-5:15 下午
 */
@Data
@ConfigurationProperties(prefix = "shiro")
public class ShiroProperties {

    private String loginUrl;

    private String anonymityUrl;

    private ShiroCacheProperties cache = new ShiroCacheProperties();

    private ShiroSessionProperties session = new ShiroSessionProperties();

}
