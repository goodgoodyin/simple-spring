package com.goodyin.springframework.beans.factory.config;

import com.goodyin.springframework.beans.factory.HierarchicalBeanFactory;
import com.goodyin.springframework.utls.StringValueResolver;

/**
 * bean配置
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    String resolveEmbeddedValue(String value);

}
