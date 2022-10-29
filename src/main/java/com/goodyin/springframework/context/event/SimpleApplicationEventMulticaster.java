package com.goodyin.springframework.context.event;


import com.goodyin.springframework.beans.factory.BeanFactory;
import com.goodyin.springframework.context.ApplicationEvent;
import com.goodyin.springframework.context.ApplicationListener;

/**
 * 事件广播器
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
