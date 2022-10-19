package com.yinhao.springframework.test.bean;

public class UserService {

    private String name;

    private String id;

    private UserDao userDao;

    public void queryUserInfoReference() {
        System.out.println("查询用户信息:" + userDao.queryUserName(id));
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
                '}';
    }
}
