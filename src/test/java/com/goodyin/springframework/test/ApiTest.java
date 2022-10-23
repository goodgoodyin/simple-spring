package com.goodyin.springframework.test;

//import com.yinhao.springframework.BeanDefinition;
//import com.yinhao.springframework.BeanFactory;
import cn.hutool.core.io.IoUtil;
import com.goodyin.springframework.beans.factory.config.BeanDefinition;
import com.goodyin.springframework.beans.factory.config.BeanReference;
import com.goodyin.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.goodyin.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.goodyin.springframework.context.support.ClassPathXmlApplicationContext;
import com.goodyin.springframework.core.io.DefaultResourceLoader;
import com.goodyin.springframework.core.io.Resource;
import com.goodyin.springframework.test.bean.UserDao;
import com.goodyin.springframework.test.bean.UserService;
import com.goodyin.springframework.beans.PropertyValue;
import com.goodyin.springframework.beans.PropertyValues;
import com.goodyin.springframework.test.common.MyBeanFactoryPostProcessor;
import com.goodyin.springframework.test.common.MyBeanPostProcessor;
import org.junit.Before;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;


import java.io.IOException;
import java.io.InputStream;

public class ApiTest {

    private static final String BEAN_NAME = "UserService";

    @Test
    public void test_BeanFactory() {

        // 1、初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2、注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition(BEAN_NAME, beanDefinition);

        // 第一次获取bean,会进行创建
        UserService userService = (UserService) beanFactory.getBean(BEAN_NAME);
        userService.queryUserInfo();

        // 第二次获取bean，会直接从Singleton中获取
        UserService userServiceSingleton = (UserService) beanFactory.getBean(BEAN_NAME);
        userServiceSingleton.queryUserInfo();
    }

    @Test
    public void test_BeanFactoryArgs() {
        // 1、初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2、注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition(BEAN_NAME, beanDefinition);

        // 3、获取bean
        UserService userService = (UserService) beanFactory.getBean(BEAN_NAME, "GoodYin");
        userService.queryUserInfo();
    }

    @Test
    public void test_BeanFactoryReference() {
        // 1、初始化
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2、UserDao注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 3、UserService 设置属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("id", "0001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        // 4、UserService注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition(BEAN_NAME, beanDefinition);

        // 5、UserService获取bean
        UserService userService = (UserService) beanFactory.getBean(BEAN_NAME);
        userService.queryUserInfoReference();
    }

    private DefaultResourceLoader resourceLoader;

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_classpath() throws IOException {
        getResource("classpath:important.properties");
    }
    private void getResource(String path) throws IOException {
        Resource resource = resourceLoader.getResource(path);
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }
    @Test
    public void test_file() throws IOException {
        getResource("src/test/resources/important.properties");
    }

    @Test
    public void test_xml(){

        // 1、初始化 beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2、读取配置文件注册bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");
        // 3、获取bean对象调用方法
        UserService userService = beanFactory.getBean(BEAN_NAME, UserService.class);
        userService.queryUserInfoReference();

    }

    @Test
    public void test_beanFactoryPostProcessorAndBeanPostProcessor() {
        // 1、初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2、读取配置文件注册bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");
        // 3、BeanDefinition加载完成，
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        // 4、Bean实例化后，修改bean信息
        MyBeanPostProcessor myBeanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(myBeanPostProcessor);
        // 5、获取bean对象调用方法
        UserService bean = beanFactory.getBean(BEAN_NAME, UserService.class);
        bean.queryUserInfoReference();

    }

    @Test
    public void test_xml_context() {

        // 1、初始化 beanFactory
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:springPostProcessor.xml");
        // 2、获取bean对象的调用方法
        UserService userService = classPathXmlApplicationContext.getBean(BEAN_NAME, UserService.class);
        userService.queryUserInfoReference();

        // 1、初始化 beanFactory
        ClassPathXmlApplicationContext classPathXmlApplicationContext1 = new ClassPathXmlApplicationContext("classpath:spring.xml");
        // 2、获取bean对象的调用方法
        UserService userService1 = classPathXmlApplicationContext1.getBean(BEAN_NAME, UserService.class);
        userService1.queryUserInfoReference();
    }

    @Test
    public void test_initAndDestroyMethod() {
        // 1、初始化 BeanFactory
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        classPathXmlApplicationContext.registerShutdownHook();
        // 2、获取Bean对象
        UserService userService = classPathXmlApplicationContext.getBean(BEAN_NAME, UserService.class);
        userService.queryUserInfoReference();
    }

    @Test
    public void test_xml_aware() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        classPathXmlApplicationContext.registerShutdownHook();

        UserService userService = classPathXmlApplicationContext.getBean(BEAN_NAME, UserService.class);
        userService.queryUserInfoReference();
//        System.out.println(userService.getApplicationContext());
//        System.out.println(userService.getBeanFactory());
    }

    @Test
    public void test_prototype() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        classPathXmlApplicationContext.registerShutdownHook();

        UserService userService1 = classPathXmlApplicationContext.getBean(BEAN_NAME, UserService.class);
        UserService userService2 = classPathXmlApplicationContext.getBean(BEAN_NAME, UserService.class);

        System.out.println(userService1);
        System.out.println(userService2);

        System.out.println(userService1 + "十六进制哈希" + Integer.toHexString(userService1.hashCode()));
        System.out.println(ClassLayout.parseInstance(userService1));
    }

    @Test
    public void test_factory_bean() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        classPathXmlApplicationContext.registerShutdownHook();

        UserService userService = classPathXmlApplicationContext.getBean(BEAN_NAME, UserService.class);
        userService.queryUserInfoReference();
    }

//    @Test
//    public void testBeanFactory() {
//        // 1、初始化 BeanFactory
//        BeanFactory beanFactory = new BeanFactory();
//
//        // 2、注册 bean
//        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
//        beanFactory.registerBeanDefinition("userService", beanDefinition);;
//
//        // 3、获取bean
//        UserService userService = (UserService) beanFactory.getBean("userService");
//        userService.queryUserInfo();
//    }
}
