package com.goodyin.springframework.test.bean.aop;

import com.goodyin.springframework.context.annotation.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AUserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "张三，北京");
        hashMap.put("10002", "李四，深圳");
    }

    public String queryUserName(String uid) {
        return hashMap.get(uid);
    }
}
