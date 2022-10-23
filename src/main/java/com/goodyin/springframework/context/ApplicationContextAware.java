package com.goodyin.springframework.context;

import com.goodyin.springframework.beans.factory.Aware;

/**
 * 感知所属的ApplicationContext接口
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext);
}
