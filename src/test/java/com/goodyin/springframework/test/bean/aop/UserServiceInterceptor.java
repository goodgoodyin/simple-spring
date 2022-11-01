package com.goodyin.springframework.test.bean.aop;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 实现拦截器
 */
public class UserServiceInterceptor implements MethodInterceptor {


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return methodInvocation.proceed();
        } finally {
            System.out.println("监控 开始 - AOP");
            System.out.println("方法名： " + methodInvocation.getMethod());
            System.out.println("方法耗时" + (System.currentTimeMillis() - start));
            System.out.println("监控结束");
        }
    }
}
