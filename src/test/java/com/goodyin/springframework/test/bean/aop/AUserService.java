package com.goodyin.springframework.test.bean.aop;

import com.goodyin.springframework.beans.factory.annotation.Autowired;
import com.goodyin.springframework.beans.factory.annotation.Value;
import com.goodyin.springframework.context.annotation.Component;

import java.util.Random;

@Component("aUserService")
public class AUserService implements IAUserService {

    @Value("${token}")
    private String token;

    @Autowired
    private AUserDao userDao;

    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userDao.queryUserName("10001") + "," + token;
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
