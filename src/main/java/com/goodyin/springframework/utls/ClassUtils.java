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

    public static boolean isCglibProxyClass(Class<?> clazz) {
        return (clazz != null && isCglibProxyClassName(clazz.getName()));
    }

    public static boolean isCglibProxyClassName(String className) {
        return (className != null && className.contains("$$"));
    }
}


