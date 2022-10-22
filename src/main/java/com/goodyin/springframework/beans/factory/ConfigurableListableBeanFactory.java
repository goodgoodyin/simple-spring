package com.goodyin.springframework.beans.factory;

import com.goodyin.springframework.beans.BeansException;
import com.goodyin.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.goodyin.springframework.beans.factory.config.BeanDefinition;
import com.goodyin.springframework.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
