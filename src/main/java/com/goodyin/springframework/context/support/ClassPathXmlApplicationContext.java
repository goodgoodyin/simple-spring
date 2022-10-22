package com.goodyin.springframework.context.support;

/**
 * 应用上下文实现类
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{

    /**
     * 配置文件地址
     */
    private String[] configurations;

    public ClassPathXmlApplicationContext() {
    }

    /**
     * 从xml中加载BeanDefinition，并刷新上下文
     * @param configuration
     */
    public ClassPathXmlApplicationContext(String configuration) {
        this(new String[]{configuration});
    }

    /**
     * 从xml中加载BeanDefinition，并刷新上下文
     * @param configurations
     */
    public ClassPathXmlApplicationContext(String[] configurations) {
        this.configurations = configurations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configurations;
    }
}
