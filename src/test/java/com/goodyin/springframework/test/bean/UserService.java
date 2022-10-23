package com.goodyin.springframework.test.bean;

import com.goodyin.springframework.beans.BeansException;
import com.goodyin.springframework.beans.factory.*;
import com.goodyin.springframework.context.ApplicationContext;
import com.goodyin.springframework.context.ApplicationContextAware;

public class UserService implements BeanNameAware, BeanClassLoadAware, BeanFactoryAware ,ApplicationContextAware {

    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;


    private String name;

    private String id;

    private String location;

    private UserDao userDao;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void queryUserInfoReference() {
        System.out.println("查询用户信息:" + userDao.queryUserName(id) + toString());
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息:" + name);
    }

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    @Override
    public void setBeanClassLoad(ClassLoader classLoader) {
        System.out.println("ClassLoader : " + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name :" + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
