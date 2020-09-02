package com.open.demo.rabbit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

@MapperScan("com.open.demo.rabbit.mapper")
@SpringBootApplication
public class RabbitApplication {

    @Autowired
    private TransactionManager transactionManager;

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(RabbitApplication.class, args);
        RabbitApplication rabbitApplication = applicationContext.getBean(RabbitApplication.class);

        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        RabbitTransactionManager rabbitTransactionManager = beanFactory.getBean(RabbitTransactionManager.class);

        System.out.println(rabbitApplication.transactionManager);
    }


//    @Bean
//    public RabbitTransactionManager rabbitTransactionManager(CachingConnectionFactory connectionFactory) {
//        return new RabbitTransactionManager(connectionFactory);
//    }

//    @Bean
//    public ChainedTransactionManager transactionManager(PlatformTransactionManager platformTransactionManager,
//                                                               RabbitTransactionManager rabbitTransactionManager) {
//        return new ChainedTransactionManager(platformTransactionManager, rabbitTransactionManager);
//    }

//    @Bean
//    @Primary
//    public PlatformTransactionManager platformTransactionManager(DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
}
