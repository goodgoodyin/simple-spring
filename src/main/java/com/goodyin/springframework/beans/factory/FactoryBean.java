package com.goodyin.springframework.beans.factory;

/**
 * 自定义对象创建接口
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
