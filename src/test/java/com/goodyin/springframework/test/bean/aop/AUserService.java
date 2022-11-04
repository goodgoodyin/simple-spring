package com.goodyin.springframework.test.bean.aop;

import com.goodyin.springframework.context.annotation.Component;

import java.util.Random;

@Component("aUserService")
public class AUserService implements IAUserService {

    private String token;

    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "goodYin, 100000, xx";
    }

    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户" + userName + "成功";
    }

    @Override
    public String toString() {
        return "AUserService{" +
                "token='" + token + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
