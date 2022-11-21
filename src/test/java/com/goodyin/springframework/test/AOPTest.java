package com.goodyin.springframework.test;

import com.goodyin.springframework.aop.AdvisedSupport;
import com.goodyin.springframework.aop.TargetSource;
import com.goodyin.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.goodyin.springframework.aop.framework.Cglib2ApoProxy;
import com.goodyin.springframework.aop.framework.JdkDynamicAopProxy;
import com.goodyin.springframework.context.support.ClassPathXmlApplicationContext;
import com.goodyin.springframework.test.bean.UserService;
import com.goodyin.springframework.test.bean.aop.AUserService;
import com.goodyin.springframework.test.bean.aop.IAUserService;
import com.goodyin.springframework.test.bean.aop.UserServiceInterceptor;
import org.junit.Test;

import java.lang.reflect.Method;

public class AOPTest {

    // 需要拦截的路径范围表达式
    private static final String EXECUTION = "execution(* com.goodyin.springframework.test.bean.aop.IAUserService.*(..))";

    /**
     * 测试拦截方法是否匹配
     */
    @Test
    public void test_ExpressionMatches() throws NoSuchMethodException {
        AspectJExpressionPointcut aspectJExpressionPointcut =
                new AspectJExpressionPointcut(EXECUTION);
        Class<UserService> userServiceClass = UserService.class;
        Method queryUserInfo = userServiceClass.getDeclaredMethod("queryUserInfo");

        // 类是否能匹配上
        boolean matches = aspectJExpressionPointcut.matches(userServiceClass);
        System.out.println(matches);

        // 方法是否能匹配上
        boolean matchesMethod = aspectJExpressionPointcut.matches(queryUserInfo, userServiceClass);
        System.out.println();
    }

    @Test
    public void test_dynamic() {
        // 目标对象
        IAUserService userService = new AUserService();

        // 组装代理对象
        AdvisedSupport advisedSupport = new AdvisedSupport();
        // 被代理的目标对象
        advisedSupport.setTargetSource(new TargetSource(userService));
        // 被代理的目标对象
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        // 方法匹配器
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut(EXECUTION));

        // jdk代理对象
        IAUserService jdkProxy = (IAUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        System.out.println("jdk:" + jdkProxy.queryUserInfo());

        // cglib代理对象
        IAUserService cglibProxy = (IAUserService) new Cglib2ApoProxy(advisedSupport).getProxy();
        System.out.println("cglib:" + cglibProxy.register("ah"));
    }

    private static final String BEAN_NAME = "aUserService";

    @Test
    public void test_spring_before_aop() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:springAop.xml");
        IAUserService userService = classPathXmlApplicationContext.getBean(BEAN_NAME, IAUserService.class);
        System.out.println("测试结果" + userService.queryUserInfo());
    }

    @Test
    public void test_property_and_scan() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring-property.xml");
        IAUserService userService = classPathXmlApplicationContext.getBean(BEAN_NAME, IAUserService.class);
        System.out.println("占位符测试结果" + userService);
        System.out.println("包扫描测试结果" + userService.queryUserInfo());
    }

    @Test
    public void test_annotation_scan() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-property.xml");
        IAUserService userService = applicationContext.getBean(BEAN_NAME, IAUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }

}
