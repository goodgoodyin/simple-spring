package com.goodyin.springframework.beans.factory;

/**
 * 感知所属的 ClassLoader 接口
 */
public interface BeanClassLoadAware extends Aware {
    void setBeanClassLoad(ClassLoader classLoader);
}
