package com.goodyin.springframework.beans.factory;

import com.goodyin.springframework.beans.BeansException;
import com.goodyin.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.goodyin.springframework.beans.factory.config.BeanDefinition;
import com.goodyin.springframework.beans.factory.config.BeanPostProcessor;
import com.goodyin.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 *
 * 提供分析和修改Bean已经预先实例化的操作接口
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例对象
     */
    void destroySingletons();
}
