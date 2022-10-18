//package com.yinhao.springframework;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * bean对象工厂, 存放bean
// */
//public class BeanFactory {
//
//    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
//
//    public Object getBean(String name) {
//        return beanDefinitionMap.get(name).getBean();
//    }
//
//    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
//        beanDefinitionMap.put(name, beanDefinition);
//    }
//}
