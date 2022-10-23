package com.goodyin.springframework.context.event;

import com.goodyin.springframework.context.ApplicationContext;
import com.goodyin.springframework.context.ApplicationEvent;

/**
 * 定义上下文事件抽象类
 * 所有事件（关闭、刷新等）都需要继承这个类
 */
public class ApplicationContextEvent extends ApplicationEvent {

    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
