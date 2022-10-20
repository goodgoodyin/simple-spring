package com.goodyin.springframework.beans.factory.config;

/**
 * 获取单例对象的接口
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);
}
