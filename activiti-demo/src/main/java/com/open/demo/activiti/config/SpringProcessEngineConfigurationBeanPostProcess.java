package com.open.demo.activiti.config;

import com.open.demo.activiti.form.DecimalFormType;
import org.activiti.engine.form.AbstractFormType;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenkechao
 * @date 2020/5/24 8:19 上午
 */
@Component
public class SpringProcessEngineConfigurationBeanPostProcess implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof SpringProcessEngineConfiguration) {

            ((SpringProcessEngineConfiguration) bean).setCustomFormTypes(customFormTypes());
        }
        return bean;
    }

    private List<AbstractFormType> customFormTypes() {
        DecimalFormType decimalFormType = new DecimalFormType();

        List<AbstractFormType> customFormTypes = new ArrayList<>();
        customFormTypes.add(decimalFormType);

        return customFormTypes;
    }
}
