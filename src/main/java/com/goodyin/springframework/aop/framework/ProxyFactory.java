package com.goodyin.springframework.aop.framework;

import com.goodyin.springframework.aop.AdvisedSupport;

/**
 * 代理工厂
 */
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createProxy().getProxy();
    }

    private AopProxy createProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2ApoProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
