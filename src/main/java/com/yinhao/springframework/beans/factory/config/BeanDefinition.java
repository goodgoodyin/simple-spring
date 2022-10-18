package com.yinhao.springframework.beans.factory.config;

/**
 * 定义Bean实例化信息
 */
public class BeanDefinition {
    /**
     * bean注册的时候祝需要注册一个类信息
     * 用class而不是Object，让Bean的实例化操作放容器里去做，而不外面创建好了再传进来
     */
    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
