package com.goodyin.springframework.beans.factory.support;

import com.goodyin.springframework.beans.BeansException;
import com.goodyin.springframework.beans.factory.BeanFactory;
import com.goodyin.springframework.beans.factory.config.BeanDefinition;

/**
 * bean工厂抽象实现类
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException{
         return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException{
        return doGetBean(name, args);
    }

    protected <T> T doGetBean(final String name, final Object[] args) {
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T)bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T)createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String name) throws BeansException;

    protected abstract Object createBean(String bean, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
