package com.goodyin.springframework.beans.factory;

/**
 * 销毁
 */
public interface DisposableBean {

    /**
     * 销毁bean
     * @throws Exception
     */
    void destroy() throws Exception;
}
