package com.goodyin.springframework.aop.aspectj;

import com.goodyin.springframework.aop.ClassFilter;
import com.goodyin.springframework.aop.MethodMatcher;
import com.goodyin.springframework.aop.Pointcut;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * 切点表达式匹配
 */
public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher {

    /**
     * PointcutPrimitive 切点表达式类型枚举
     */
    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVE = new HashSet<>();

    static {
        // EXECUTION: 表示某个方法的执行
        SUPPORTED_PRIMITIVE.add(PointcutPrimitive.EXECUTION);
    }

    // 切点表达式
    private final PointcutExpression pointcutExpression;

    /**
     *
     * @param expression 表达式
     */
    public AspectJExpressionPointcut(String expression) {
        PointcutParser pointcutParser =
                PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORTED_PRIMITIVE, this.getClass().getClassLoader());
        this.pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }

    /**
     * 类匹配
     * @param clazz
     * @return
     */
    @Override
    public boolean matches(Class<?> clazz) {
        return pointcutExpression.couldMatchJoinPointsInType(clazz);
    }

    /**
     * 方法匹配
     * @param method
     * @param targetClass
     * @return
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return pointcutExpression.matchesMethodExecution(method).alwaysMatches();
    }

    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }
}
