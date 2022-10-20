package com.goodyin.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static Map<String, String> hashMap = new HashMap<>();
    static {
        hashMap.put("0001", "a");
        hashMap.put("0002", "b");
    }
    public String queryUserName(String id) {
        return hashMap.get(id);
    }
}
