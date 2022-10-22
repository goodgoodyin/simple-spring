package com.goodyin.springframework.beans.factory.config;

import com.goodyin.springframework.beans.BeansException;
import com.goodyin.springframework.beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {
    /**
     * 修改 BeanDefinition 属性机制
     * (在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前)
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
