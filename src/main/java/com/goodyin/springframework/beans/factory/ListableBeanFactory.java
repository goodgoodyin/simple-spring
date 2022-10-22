package com.goodyin.springframework.beans.factory;

import com.goodyin.springframework.beans.BeansException;

import java.util.Map;

/**
 * 根据类型获取bean实例
 */
public interface ListableBeanFactory extends BeanFactory{

    /**
     * 按照类型返回 Bean 实例
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回注册表中所有的bean
     * @return
     */
    String[] getBeanDefinitionNames();

}
