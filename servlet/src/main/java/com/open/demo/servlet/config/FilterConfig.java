package com.open.demo.servlet.config;

import com.open.demo.servlet.filter.RequestResponseLoggingFilter;
import com.open.demo.servlet.filter.TransactionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.LinkedList;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-12-27-2:24 下午
 */
@Configuration
public class FilterConfig {

    @Order(2)
    @Bean
    public FilterRegistrationBean<TransactionFilter> transactionFilterRegistrationBean() {

        FilterRegistrationBean<TransactionFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TransactionFilter());
        LinkedList<String> list = new LinkedList<>();
        list.add("/*");
        registrationBean.setUrlPatterns(list);

        return registrationBean;
    }

    @Order(1)
    @Bean
    public FilterRegistrationBean<RequestResponseLoggingFilter> loggingFilterRegistrationBean() {

        FilterRegistrationBean<RequestResponseLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestResponseLoggingFilter());
        LinkedList<String> list = new LinkedList<>();
        list.add("/*");
        registrationBean.setUrlPatterns(list);

        return registrationBean;
    }
}
