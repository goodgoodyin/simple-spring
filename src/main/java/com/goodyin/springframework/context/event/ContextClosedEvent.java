package com.goodyin.springframework.context.event;

/**
 * 关闭事件
 */
public class ContextClosedEvent extends ApplicationContextEvent {

    public ContextClosedEvent(Object source) {
        super(source);
    }
}
