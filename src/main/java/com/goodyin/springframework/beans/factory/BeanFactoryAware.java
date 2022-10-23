package com.goodyin.springframework.beans.factory;

import com.goodyin.springframework.beans.BeansException;

/**
 * 感知所属的BeanFactory接口
 */
public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
