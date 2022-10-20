package com.goodyin.springframework.beans.factory.support;

import com.goodyin.springframework.beans.BeansException;
import com.goodyin.springframework.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Cglib实例化
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if (null == constructor) return  enhancer.create();

        return enhancer.create(constructor.getParameterTypes(), args);
    }
}
