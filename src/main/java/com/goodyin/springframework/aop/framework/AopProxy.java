package com.goodyin.springframework.aop.framework;

/**
 * 代理抽象实现
 */
public interface AopProxy {

    /**
     * 获取代理对象
     * @return
     */
    Object getProxy();

}
