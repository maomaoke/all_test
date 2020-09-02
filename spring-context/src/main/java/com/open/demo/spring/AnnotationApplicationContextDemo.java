package com.open.demo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author chenkechao
 * @date 2020/2/16 12:08 下午
 */
public class AnnotationApplicationContextDemo {

    @Autowired
    private InnerBean innerBean;

    @Bean
    public InnerBean innerBean() {
        return new InnerBean();
    }

    private static class InnerBean {
        private String name;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotationApplicationContextDemo.class);
        context.refresh();
        int definitionCount = context.getBeanDefinitionCount();

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println("beanDefinitionNames == " + Arrays.toString(beanDefinitionNames) + "\ndefinitionCount ==" + definitionCount);
    }
}
