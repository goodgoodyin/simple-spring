package com.goodyin.springframework.context.annotation;

import java.lang.annotation.*;

/**
 * 配置作用域
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {

    String value() default "singleton";

}
