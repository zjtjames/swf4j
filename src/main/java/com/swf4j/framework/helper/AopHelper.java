/**
 * created by Zheng Jiateng on 2019/5/25.
 */
package com.swf4j.framework.helper;

import com.swf4j.framework.annotation.Aspect;
import com.swf4j.framework.annotation.Service;
import com.swf4j.framework.proxy.AspectProxy;
import com.swf4j.framework.proxy.Proxy;
import com.swf4j.framework.proxy.ProxyManager;
import com.swf4j.framework.proxy.TransactionProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Aop助手类（方法拦截助手类）
 */
public final class AopHelper {

    private static Logger logger = LoggerFactory.getLogger(AopHelper.class);

    /**
     * 初始化整个AOP框架
     */
    static {
        try {
            Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap(); // 切面类与目标类集合的映射
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap); // 目标类与增强对象列表的映射
            for (Map.Entry<Class<?>, List<Proxy>> targetEntry : targetMap.entrySet()) {
                Class<?> targetClass = targetEntry.getKey();
                List<Proxy> proxyList = targetEntry.getValue();
                // 生成代理对象
                Object proxy = ProxyManager.createProxy(targetClass, proxyList);
                // 用代理对象替换 BeanMap中targetClass原来的值 即目标对象
                BeanHelper.setBean(targetClass, proxy);
            }
        } catch (Exception e) {
            logger.error("initialize aop failure", e);
        }
    }

    /**
     * 获取目标类集合 如@Aspect(Controller.class) 则目标类集合是所有被@Controller注解标记的类
     */
    private static Set<Class<?>> createTargetClassSet(Aspect aspect) throws Exception {
        Set<Class<?>> targetClassSet = new HashSet<>();
        // 获取@Aspect注解的值，即标记目标类的注解
        Class<? extends Annotation> annotation = aspect.value();
        if (annotation != null && !annotation.equals(Aspect.class)) {
            targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }
        return targetClassSet;
    }

    /**
     * 获得切面类与目标类的映射关系
     * 切面类需要继承AspectProxy抽象类，还需要带有@Aspect注解，只有满足这两个条件，才能根据@Aspect注解的value中的注解
     * 去获取该注解所对应的目标类集合，然后才能建立切面类与目标类集合之间的映射关系
     */
    private static void addAspectProxy(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception {
        // 获取所有继承了AspectProxy抽象类的切面类
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        // 从中找被@Aspect注解标记的类 并且根据@Aspect的value中的注解，找出每个切面类对应的目标类集合，放入proxyMap中
        for (Class<?> proxyClass : proxyClassSet) {
            if (proxyClass.isAnnotationPresent(Aspect.class)) {
                Aspect aspect = proxyClass.getAnnotation(Aspect.class);
                Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                proxyMap.put(proxyClass, targetClassSet);
            }
        }
    }

    // @Transaction是注解在方法上的 所以AopHelper先抓所有的Service类，再在增强类内部判断此方法是否要进行事务控制
    private static void addTransactionProxy(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception {
        Set<Class<?>> serviceClassSet = ClassHelper.getClassSetByAnnotation(Service.class);
        proxyMap.put(TransactionProxy.class, serviceClassSet);
    }


    private static Map<Class<?>, Set<Class<?>>> createProxyMap() throws Exception {
        Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<>();
        addAspectProxy(proxyMap);
        addTransactionProxy(proxyMap);
        return proxyMap;
    }

    /**
     * 根据上一个方法得到的切面类与目标类集合之间的映射关系proxyMap
     * 找出目标类与增强对象列表的映射关系targetMap,用于之后传给代理链ProxyChain
     * ProxyChain中的List<Proxy> proxyList
     */
    private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception {
        Map<Class<?>, List<Proxy>> targetMap = new HashMap<>();
        for (Map.Entry<Class<?>, Set<Class<?>>> proxyEntry : proxyMap.entrySet()) {
            Class<?> proxyClass = proxyEntry.getKey();
            Set<Class<?>> targetClassSet = proxyEntry.getValue();
            for (Class<?> targetClass : targetClassSet) {
                Proxy proxy = (Proxy) proxyClass.newInstance();
                if (targetMap.containsKey(targetClass)) {
                    targetMap.get(targetClass).add(proxy);
                } else {
                    List<Proxy> proxyList = new ArrayList<>();
                    proxyList.add(proxy);
                    targetMap.put(targetClass, proxyList);
                }
            }
        }
        return targetMap;
    }

}
