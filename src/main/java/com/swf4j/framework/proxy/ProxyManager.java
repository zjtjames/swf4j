/**
 * created by Zheng Jiateng on 2019/5/13.
 */
package com.swf4j.framework.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 代理管理器，提供一个创建代理对象的方法，输入一个目标类和一组Proxy接口实现，输出一个代理对象
 */
public class ProxyManager {

    @SuppressWarnings("unchecked") // 使用unchecked忽略编译时的向下转型警告
    public static <T> T createProxy(Class<T> targetClass, List<Proxy> proxyList) {
        return (T) Enhancer.create(targetClass, new MethodInterceptor() {
            @Override
            public Object intercept(Object targetObject, Method targetMethod, Object[] methodParams, MethodProxy methodProxy) throws Throwable {
                return new ProxyChain(targetClass, targetObject, targetMethod, methodProxy, methodParams, proxyList).doProxyChain();
            }
        });
    }
}
