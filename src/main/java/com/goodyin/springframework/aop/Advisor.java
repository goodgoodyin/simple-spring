package com.goodyin.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * Advisor访问者
 */
public interface Advisor {


    Advice getAdvice();

}
