package com.goodyin.springframework.beans.factory.config;

import com.goodyin.springframework.beans.PropertyValues;

/**
 * 定义Bean实例化信息
 */
public class BeanDefinition {
    /**
     * bean注册的时候祝需要注册一个类信息
     * 用class而不是Object，让Bean的实例化操作放容器里去做，而不外面创建好了再传进来
     */
    private Class beanClass;

    /**
     * bean属性信息
     */
    private PropertyValues propertyValues;

    private String initMethodName;

    private String destroyMethodName;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }
}
