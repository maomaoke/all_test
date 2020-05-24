package com.open.demo.activiti.form;

import org.activiti.engine.form.AbstractFormType;

import java.math.BigDecimal;

/**
 * @author chenkechao
 * @date 2020/5/24 8:22 上午
 */
public class DecimalFormType extends AbstractFormType {
    @Override
    public Object convertFormValueToModelValue(String propertyValue) {
        return new BigDecimal(propertyValue);
    }

    @Override
    public String convertModelValueToFormValue(Object modelValue) {
        return modelValue.toString();
    }

    @Override
    public String getName() {
        return "decimal";
    }
}
