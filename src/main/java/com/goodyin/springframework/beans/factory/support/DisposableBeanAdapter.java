package com.goodyin.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.goodyin.springframework.beans.BeansException;
import com.goodyin.springframework.beans.factory.DisposableBean;
import com.goodyin.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * 销毁方法适配器
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String beanName;

    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        // 1、 实现接口
        if (bean instanceof DisposableBean) {
            ((DisposableBean)bean).destroy();

        }

        // 2、配置信息
        if (StrUtil.isNotEmpty(destroyMethodName)
                && !(bean instanceof DisposableBean && "destroy".equals(destroyMethodName))) {
            Method method = bean.getClass().getMethod(destroyMethodName);
            if (null == method) {
                throw new BeansException("没有找到 "+ beanName + "的销毁方法" );
            }
            method.invoke(bean);
        }

    }
}
