package com.goodyin.springframework.context;

/**
 * 事件发布者
 */
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);
}
