/**
 * created by Zheng Jiateng on 2019/4/22.
 */
package com.swf4j.framework.helper;


import com.swf4j.framework.annotation.Inject;
import com.swf4j.framework.util.ReflectionUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 依赖注入助手类 ： 通过DI实现Ioc 将某个类需要依赖的成员注入到这个类中
 */
public final class IocHelper {

    static {
        // 获取所有Bean类(类比于spring中所有继承了@Component的注解)与Bean实例之间的映射关系 Bean Map
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (MapUtils.isNotEmpty(beanMap)) {
            // 遍历beanMap
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                // 从beanMap中获取Bean类与Bean实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                // 获取Bean类定义的所有成员变量
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtils.isNotEmpty(beanFields)) {
                    // 遍历beanFields
                    for (Field beanField : beanFields) {
                        // 判断当前beanField是否带有@Inject注解
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            // 在beanMap中获取beanField对应的实例
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null) {
                                // 通过反射初始化beanField的值
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
