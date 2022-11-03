package com.goodyin.springframework.aop;

public interface PointcutAdvisor extends Advisor {
    /**
     * 获取切点
     * @return
     */
    Pointcut getPointCut();
}
