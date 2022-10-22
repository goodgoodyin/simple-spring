package com.goodyin.springframework.beans.factory.config;

import com.goodyin.springframework.beans.BeansException;

/**
 * 提供修改新实例化Bean对象的扩展点
 */
public interface BeanPostProcessor {

    /**
     * 修改 BeanDefinition 属性，在对象执行方法前
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws  BeansException;

    /**
     * 修改 BeanDefinition 属性，在对象执行方法后
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
