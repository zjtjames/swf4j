/**
 * created by Zheng Jiateng on 2019/5/13.
 */
package com.swf4j.framework.proxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 一个抽象类 提供Proxy的一个模板方法，并在该抽象类的具体实现中扩展相应的方法
 */
public abstract class AspectProxy implements Proxy {

    private static Logger logger = LoggerFactory.getLogger(AspectProxy.class);

    @Override
    // 标为final 不让子类改此方法，只让子类实现下面几个非final方法
    public final Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result = null;

        Class<?> cls = proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethodParams();

        begin();
        try {
            // 如果此方法需要拦截
            // AopHelper先抓所有的目标类，再在增强类内部判断此方法是否要进行增强，实现方法级别的拦截
            if (intercept(cls, method, params)) {
                before(cls, method, params);
                result = proxyChain.doProxyChain();
                after(cls, method, params, result);
            } else {
                // 若此方法不需要拦截 则直接进代理链中的下一个Proxy对象
                result = proxyChain.doProxyChain();
            }
        } catch (Exception e) {
            // 若有抛出 则抛出增强
            logger.error("proxy failure", e);
            error(cls, method, params, e);
            throw e;
        } finally {
            end();
        }
        return result;
    }

    // 以下方法都非final 可以覆盖
    public void begin() {
    }

    // 根据切面的切点表达式判断此方法是否要拦截
    public boolean intercept(Class<?> cls, Method method, Object[] params) throws Throwable {
        return true;
    }

    // 前置增强
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
    }

    // 后置增强
    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
    }

    // 抛出增强
    public void error(Class<?> cls, Method method, Object[] params, Throwable e) {
    }

    public void end(){}
}
