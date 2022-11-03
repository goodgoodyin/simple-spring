package com.goodyin.springframework.test.bean.aop;

import com.goodyin.springframework.aop.MethodBeforeAdvice;

/**
 * 自定义拦截方法
 */
import java.lang.reflect.Method;

public class AUserServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法：" + method.getName());
    }
}
