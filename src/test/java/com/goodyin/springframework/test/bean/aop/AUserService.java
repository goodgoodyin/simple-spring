package com.goodyin.springframework.test.bean.aop;

import java.util.Random;

public class AUserService implements IAUserService {

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
}
