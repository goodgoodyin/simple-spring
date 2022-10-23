package com.goodyin.springframework.beans.factory;

/**
 * 初始化
 */
public interface InitializingBean {

    /**
     * bean 处理属性填充后调用
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
