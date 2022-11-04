package com.goodyin.springframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.goodyin.springframework.beans.factory.config.BeanDefinition;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 处理对象扫描并转换为BeanDefinition
 */
public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponents(String beanPackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        // 扫描beanPackage下到所有带Component注解到类
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(beanPackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }
}
