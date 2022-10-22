package com.goodyin.springframework.beans.factory;

import com.goodyin.springframework.beans.BeansException;

/**
 * bean对象工厂, 存放bean
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType);

}
