package com.goodyin.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static Map<String, String> hashMap = new HashMap<>();

    private void initDataMethod() {
        System.out.println("执行 init-method");
        hashMap.put("0001", "a");
        hashMap.put("0002", "b");
    }
    public void destroyDataMethod() {
        System.out.println("执行 destroy-method");
        hashMap.clear();
    }

    public String queryUserName(String id) {
        return hashMap.get(id);
    }
}
