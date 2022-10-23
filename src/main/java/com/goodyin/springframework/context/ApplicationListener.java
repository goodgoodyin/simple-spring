package com.goodyin.springframework.context;

import java.util.EventListener;

/**
 * 监听接口
 * @param <E>
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);
}
