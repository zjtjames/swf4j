/**
 * created by Zheng Jiateng on 2019/4/22.
 */
package com.swf4j.framework.helper;

import com.swf4j.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Bean助手类 : 相当于一个Bean容器，因为BEAN_MAP中存放了Bean类与Bean实例的映射关系，通过调用getBean方法，传入一个Bean类，获取Bean实例
 *
 * 调用ClassHelper类的getBeanClassSet方法，获取所有被slf4j框架管理的Bean类；
 * 随后循环调用ReflectionUtil的newInstance方法，根据类来实例化对象，最后将每次创建的对象存在一个静态的Map<Class<?>, Object>中。
 * 我们需要随时获取该Map，通过key(类名)去获取value(Bean对象)
 */
public final class BeanHelper {

    /**
     * 定义Bean映射(用于存放Bean类与Bean实例的映射关系)
     */
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        // 根据类来实例化对象
        for (Class<?> beanClass : beanClassSet) {
            Object obj = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass, obj);
        }
    }

    /**
     * 获取Bean映射
     */
    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    /**
     * 获取Bean实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> cls) {
        if (!BEAN_MAP.containsKey(cls)) {
            throw new RuntimeException("can not get bean by class: " + cls);
        }
        return (T) BEAN_MAP.get(cls);
    }

}
