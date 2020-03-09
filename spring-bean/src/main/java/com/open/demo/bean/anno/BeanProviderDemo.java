package com.open.demo.bean.anno;

import com.open.demo.bean.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author chenkechao
 * @date 2020/2/22 10:45 上午
 */
public class BeanProviderDemo {

    @Autowired
    private User user;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(BeanProviderDemo.class);

        applicationContext.refresh();

        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();

        User bean = applicationContext.getBean(User.class);

        applicationContext.close();
    }

    @Bean
    public User user() {
        return new User("陈可超");
    }
}
