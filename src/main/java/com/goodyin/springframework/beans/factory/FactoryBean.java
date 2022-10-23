package com.goodyin.springframework.beans.factory;

/**
 * 对象创建
 * @param <T>
 */
public interface FactoryBean<T> {

    /**
     * 获取对象
     * @return
     * @throws Exception
     */
    T getObject() throws Exception;

    /**
     * 对象类型
     * @return
     */
    Class<?> getObjectType();

    /**
     * 是否单例对象
     * @return
     */
    boolean isSingleton();
}
