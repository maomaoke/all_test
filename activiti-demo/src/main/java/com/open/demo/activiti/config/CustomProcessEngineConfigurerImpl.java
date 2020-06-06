package com.open.demo.activiti.config;

import com.open.demo.activiti.form.DecimalFormType;
import com.open.example.activiti.boot.CustomProcessEngineConfigurationConfigurer;
import org.activiti.engine.form.AbstractFormType;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenkechao
 * @date 2020/5/24 12:35 下午
 */
@Component
public class CustomProcessEngineConfigurerImpl implements CustomProcessEngineConfigurationConfigurer {

    @Override
    public void configure(SpringProcessEngineConfiguration processEngineConfiguration) {


        processEngineConfiguration.setCustomFormTypes(customFormTypes());
    }

    private List<AbstractFormType> customFormTypes() {
        DecimalFormType decimalFormType = new DecimalFormType();

        List<AbstractFormType> customFormTypes = new ArrayList<>();
        customFormTypes.add(decimalFormType);

        return customFormTypes;
    }
}
