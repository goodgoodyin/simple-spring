package com.goodyin.springframework.core.io;

/**
 * 包资源记载器
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 根据路径获取资源
     * @param location
     * @return
     */
    Resource getResource(String location) ;
}
