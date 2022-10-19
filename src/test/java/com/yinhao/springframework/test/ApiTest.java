package com.yinhao.springframework.test;

//import com.yinhao.springframework.BeanDefinition;
//import com.yinhao.springframework.BeanFactory;
import com.yinhao.springframework.beans.PropertyValue;
import com.yinhao.springframework.beans.PropertyValues;
import com.yinhao.springframework.beans.factory.BeanFactory;
import com.yinhao.springframework.beans.factory.config.BeanDefinition;
import com.yinhao.springframework.beans.factory.config.BeanReference;
import com.yinhao.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.yinhao.springframework.test.bean.UserDao;
import com.yinhao.springframework.test.bean.UserService;
import org.junit.Test;

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
