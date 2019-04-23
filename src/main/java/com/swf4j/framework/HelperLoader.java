/**
 * created by Zheng Jiateng on 2019/4/23.
 */
package com.swf4j.framework;

import com.swf4j.framework.helper.BeanHelper;
import com.swf4j.framework.helper.ClassHelper;
import com.swf4j.framework.helper.ControllerHelper;
import com.swf4j.framework.helper.IocHelper;
import com.swf4j.framework.util.ClassUtil;

/**
 * ClassHelper, BeanHelper, IocHelper, ControllerHelper的入口程序，用来加载他们，实际上是加载他们的静态块
 */
public final class HelperLoader {

    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }
}
