package com.open.demo.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * todo description
 *
 * @author CHEN-KE-CHAO
 * @date 2020-02-19-9:16 下午
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application-context.xml");

//        ServiceLoader serviceLoader = applicationContext.getBean("serviceFactoryBean", ServiceLoader.class);
//        for (Object o : serviceLoader) {
//            System.out.println(o);
//        }
        Object user = applicationContext.getBean("user");
        System.out.println(user);
    }


}
