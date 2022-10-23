package com.goodyin.springframework.context.support;


import com.goodyin.springframework.beans.BeansException;
import com.goodyin.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.goodyin.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.goodyin.springframework.beans.factory.config.BeanPostProcessor;
import com.goodyin.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.goodyin.springframework.context.ApplicationContextAware;
import com.goodyin.springframework.context.ApplicationEvent;
import com.goodyin.springframework.context.ApplicationListener;
import com.goodyin.springframework.context.ConfigurableApplicationContext;
import com.goodyin.springframework.context.event.ApplicationEventMulticaster;
import com.goodyin.springframework.context.event.ContextClosedEvent;
import com.goodyin.springframework.context.event.ContextRefreshedEvent;
import com.goodyin.springframework.context.event.SimpleApplicationEventMulticaster;
import com.goodyin.springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * 应用上下文抽象实现
 * 处理资源加载过程
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() throws BeansException {
        // 1、创建 BeanFactory，并加载BeanDefinition
        refreshBeanFactory();

        // 2、获取 BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3、添加ApplicationContextAwareProcessor
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 4、在bean实例化前，执行BeanFactoryProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        // 5、BeanPostProcessors 需要提前于其他Bean对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        // 6、初始化事件发布者
        initApplicationEventMulticaster();

        // 7、注册事件监听器
        registerListeners();

        // 8、提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();

        // 9、发布容器刷新完成事件
        finishedRefresh();
    }

    private void finishedRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener applicationListener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(applicationListener);
        }
    }

    private void initApplicationEventMulticaster() {
        ConfigurableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);

    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory() throws BeansException;

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beansOfType = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beansOfType.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beansOfType = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beansOfType.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return getBeanFactory().getBean(name, requiredType);
    }

    /**
     * 向虚拟机注册钩子
     */
    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        // 发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));

        // 执行销毁单例bean对销毁方法
        getBeanFactory().destroySingletons();
    }
}
