package com.goodyin.springframework.context.event;

/**
 * 刷新事件
 */
public class ContextRefreshedEvent extends ApplicationContextEvent{

    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
