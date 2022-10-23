package com.goodyin.springframework.test.bean;

import com.goodyin.springframework.beans.factory.DisposableBean;
import com.goodyin.springframework.beans.factory.InitializingBean;

public class UserService implements InitializingBean, DisposableBean {

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
    public void destroy() throws Exception {
        System.out.println("执行 UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行UserService.afterPropertiesSet");
    }
}
