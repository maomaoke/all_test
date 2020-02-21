package com.open.demo.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * @author chenkechao
 * @date 2020/2/16 12:08 下午
 */
public class AnnotationApplicationContextDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotationApplicationContextDemo.class);

        int definitionCount = context.getBeanDefinitionCount();
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println("beanDefinitionNames == " + Arrays.toString(beanDefinitionNames) + "\ndefinitionCount ==" + definitionCount);
    }
}
