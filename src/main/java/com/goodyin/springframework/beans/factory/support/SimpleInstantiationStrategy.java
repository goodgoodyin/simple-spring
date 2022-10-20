package com.goodyin.springframework.beans.factory.support;

import com.goodyin.springframework.beans.BeansException;
import com.goodyin.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * JDK实例化
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException {
        Class clazz = beanDefinition.getBeanClass();

        try {
            if (null != constructor) {
                return clazz.getDeclaredConstructor(constructor.getParameterTypes())
                        .newInstance(args);
            } else {
                  return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new BeansException("实例化【 " + clazz.getName() + "】失败", e);
        }

    }
}
