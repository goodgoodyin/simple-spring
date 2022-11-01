package com.goodyin.springframework.aop;

/**
 * 定义匹配类，用于切点找到给定的接口和目标类
 */
public interface ClassFilter {

    boolean matches(Class<?> clazz);

}
