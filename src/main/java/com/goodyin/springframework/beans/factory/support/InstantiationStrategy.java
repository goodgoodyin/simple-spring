package com.goodyin.springframework.beans.factory.support;

import com.goodyin.springframework.beans.BeansException;
import com.goodyin.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 实例化策略
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition,
                       String beanName,
                       Constructor constructor,
                       Object[] args) throws BeansException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
