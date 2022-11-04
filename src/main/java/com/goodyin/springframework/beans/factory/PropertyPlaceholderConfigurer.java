package com.goodyin.springframework.beans.factory;

import com.goodyin.springframework.beans.BeansException;
import com.goodyin.springframework.beans.PropertyValue;
import com.goodyin.springframework.beans.PropertyValues;
import com.goodyin.springframework.beans.factory.config.BeanDefinition;
import com.goodyin.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.goodyin.springframework.core.io.DefaultResourceLoader;
import com.goodyin.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

/**
 * 处理占位符配置
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {


        try {
            // 加载属性文件
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) continue;
                    String strVal = String.valueOf(value);
                    StringBuilder builder = new StringBuilder(strVal);
                    int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                    int stopIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
                    if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
                        String propKey = strVal.substring(startIdx + 2, stopIdx);
                        // 通过占位符提取到配置信息
                        String propVal = properties.getProperty(propKey);
                        builder.replace(startIdx, stopIdx + 1, propVal);
                        propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), builder.toString()));
                    }
                }
            }
        } catch (IOException e) {
            throw new BeansException("加载参数报错 ", e);
        }



    }
}
