package com.goodyin.springframework.context.event;

import com.goodyin.springframework.context.ApplicationEvent;
import com.goodyin.springframework.context.ApplicationListener;

/**
 * 事件广播器
 */
public interface ApplicationEventMulticaster {

    /**
     * 添加监听
     * @param applicationListener
     */
    void addApplicationListener(ApplicationListener<?> applicationListener);

    /**
     * 删除监听
     * @param applicationListener
     */
    void removeApplicationListener(ApplicationListener<?> applicationListener);

    /**
     * 广播事件
     * @param event
     */
    void multicastEvent(ApplicationEvent event);
}
