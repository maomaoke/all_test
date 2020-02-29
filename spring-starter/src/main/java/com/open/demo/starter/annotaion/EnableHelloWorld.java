package com.open.demo.starter.annotaion;

import com.open.demo.starter.configuration.HelloWorldConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author chenkechao
 * @date 2020/2/1 5:42 下午
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
//@Import(HelloWorldImportSelector.class)
@Import(HelloWorldConfiguration.class)
public @interface EnableHelloWorld {
}
