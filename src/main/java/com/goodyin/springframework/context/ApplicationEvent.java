package com.goodyin.springframework.context;

import java.util.EventObject;

/**
 * 定义和实现事件
 */
public abstract class ApplicationEvent extends EventObject {

    public ApplicationEvent(Object source) {
        super(source);
    }
}
