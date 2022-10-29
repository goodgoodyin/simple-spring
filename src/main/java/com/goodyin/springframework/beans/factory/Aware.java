package com.goodyin.springframework.beans.factory;

/**
 * 标记类接口,实现该接口可以被Spring容器感知
 * 只要实现了Aware自接口的Bean都能获取到对应到Spring底层组件
 */
public interface Aware {
}
