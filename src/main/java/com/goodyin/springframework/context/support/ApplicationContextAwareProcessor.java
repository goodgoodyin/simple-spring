package com.goodyin.springframework.context.support;

import com.goodyin.springframework.beans.BeansException;
import com.goodyin.springframework.beans.factory.config.BeanPostProcessor;
import com.goodyin.springframework.context.ApplicationContext;
import com.goodyin.springframework.context.ApplicationContextAware;

/**
 * 包装处理器
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 把ApplicationContext包装到ApplicationContextAware里去
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware)bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
