package com.swf4j.framework.annotation;

import java.lang.annotation.*;

/**
 * 切面注解 切面=切点+增强
 */

@Target(ElementType.TYPE) // TYPE表示用于类、接口、枚举、注解
@Retention(RetentionPolicy.RUNTIME) // 运行时加载到jvm中 有效范围最大
public @interface Aspect {
    /**
     * 注解
     */
    Class<? extends Annotation> value();
}
