package com.goodyin.springframework.context;

import com.goodyin.springframework.beans.factory.HierarchicalBeanFactory;
import com.goodyin.springframework.beans.factory.ListableBeanFactory;
import com.goodyin.springframework.core.io.ResourceLoader;

/**
 * 定义上下文
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {

}
