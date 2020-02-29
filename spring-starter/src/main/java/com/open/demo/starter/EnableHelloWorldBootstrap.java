package com.open.demo.starter;

import com.open.demo.starter.annotaion.EnableHelloWorld;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author chenkechao
 * @date 2020/2/1 5:47 下午
 */
@EnableHelloWorld
public class EnableHelloWorldBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(EnableHelloWorldBootstrap.class).web(WebApplicationType.NONE).run(args);
        String s = context.getBean("helloWorld", String.class);
        System.out.println(s);
        context.close();
    }
}
