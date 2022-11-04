package com.goodyin.springframework.context.annotation;

import java.lang.annotation.*;

/**
 * 配置到class类上，注册bean
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";
}
