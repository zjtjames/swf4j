/**
 * created by Zheng Jiateng on 2019/4/23.
 */
package com.swf4j.framework;

import com.swf4j.framework.helper.*;
import com.swf4j.framework.util.ClassUtil;

/**
 * ClassHelper, BeanHelper, IocHelper, ControllerHelper的入口程序，用来加载他们，实际上是加载他们的静态块
 */
public final class HelperLoader {

    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                // AopHelper要在IocHelper之前加载，因为首先要通过AopHelper获取代理对象，然后才能IocHelper进行依赖注入
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }
}
