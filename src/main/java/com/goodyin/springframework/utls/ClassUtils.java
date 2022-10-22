package com.goodyin.springframework.utls;

/**
 * 获取classLoader
 */
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        ClassLoader contextClassLoader = null;
        try {
            Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {

        }

        if (contextClassLoader == null) {
            contextClassLoader = ClassUtils.class.getClassLoader();
        }
        return contextClassLoader;
    }
}


