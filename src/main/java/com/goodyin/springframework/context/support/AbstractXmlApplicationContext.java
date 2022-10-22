package com.goodyin.springframework.context.support;

import com.goodyin.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.goodyin.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 上下文中对xml配置信息对加载
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    /**
     * 处理xml相关信息
     * @param beanFactory
     */
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
