package com.goodyin.springframework.beans.factory.config;

import com.goodyin.springframework.beans.BeansException;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName)
            throws BeansException;
}
