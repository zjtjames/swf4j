/**
 * created by Zheng Jiateng on 2019/5/13.
 */
package com.swf4j.framework.proxy;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 代理链
 */
public class ProxyChain {

    private final Class<?> targetClass; // 目标类
    private final Object targetObject; // 目标对象
    private final Method targetMethod; // 目标方法
    private final MethodProxy methodProxy; // CGLib提供的一个方法代理对象，在doProxyChain方法中被使用
    private final Object[] methodParams; // 方法参数

    private List<Proxy> proxyList = new ArrayList<>(); // 代理列表
    private int proxyIndex = 0; // 代理索引

    public ProxyChain(Class<?> targetClass, Object targetObject, Method targetMethod, MethodProxy methodProxy,
                      Object[] methodParams, List<Proxy> proxyList) { // 成员变量在构造器中进行初始化
        this.targetClass = targetClass;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.methodProxy = methodProxy;
        this.methodParams = methodParams;
        this.proxyList = proxyList;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public Object[] getMethodParams() {
        return methodParams;
    }

    public Object doProxyChain() throws Throwable {
        Object methodResult;
        if (proxyIndex < proxyList.size()) {
            methodResult = proxyList.get(proxyIndex++).doProxy(this);
        } else {
            // 执行目标对象的业务逻辑
            methodResult = methodProxy.invokeSuper(targetObject, methodParams);
        }
        return methodResult;
    }
}
