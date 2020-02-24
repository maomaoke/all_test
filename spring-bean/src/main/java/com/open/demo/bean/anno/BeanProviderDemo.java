package com.open.demo.bean.anno;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;

/**
 * @author chenkechao
 * @date 2020/2/22 10:45 上午
 */
public class BeanProviderDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(BeanProviderDemo.class);

        applicationContext.refresh();

        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = beanFactory;
            Map<String, String> beanMap = listableBeanFactory.getBeansOfType(String.class);
            System.out.println(beanMap);
        }


        applicationContext.close();
    }

    @Bean
    public String helloWorld() {
        return "Hello, World";
    }

    @Bean
    public String java() {
        return "java";
    }
}
