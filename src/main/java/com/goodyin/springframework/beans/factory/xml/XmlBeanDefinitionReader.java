package com.goodyin.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.goodyin.springframework.beans.BeansException;
import com.goodyin.springframework.beans.PropertyValue;
import com.goodyin.springframework.beans.factory.config.BeanDefinition;
import com.goodyin.springframework.beans.factory.config.BeanReference;
import com.goodyin.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.goodyin.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.goodyin.springframework.core.io.Resource;
import com.goodyin.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * 解析XML处理been注册
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {


    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadDefinition(inputStream);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException("解析xml报错：" + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for(Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    /**
     *  解析并注册bean
     * @param inputStream
     * @throws ClassNotFoundException
     */
    protected void doLoadDefinition(InputStream inputStream) throws ClassNotFoundException {
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        // 解析加载bean
        for (int i = 0; i < childNodes.getLength(); i++) {
            // 判断元素、对象
            if (!(childNodes.item(i) instanceof Element)
                    || !"bean".equals(childNodes.item(i).getNodeName())) {
                continue;
            }
            // 解析标签
            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            Class<?> clazz = Class.forName(className);
            // 获取bean名称，优先级 id > name
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("有重复bean：" + beanName);
            }

            // 定义Bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            // 属性填充
            for (int j = 0; j < bean.getChildNodes().getLength(); j ++) {
                Node item = bean.getChildNodes().item(j);
                if (!(item instanceof Element) || !"property".equals(item.getNodeName())) {
                    continue;
                }
                // 解析标签
                Element property = (Element) item;
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");
                // 获取属性值：引入对象、值对象
                // todo 不大理解这句
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrName) : attrValue;
                // 创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            // 注册
            getRegistry().registerBeanDefinition(beanName,  beanDefinition);
        }
    }
}
