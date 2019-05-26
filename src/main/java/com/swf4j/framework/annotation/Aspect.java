package com.swf4j.framework.annotation;

import java.lang.annotation.*;

/**
 * 切面注解 切面=切点+增强
 */

@Target(ElementType.TYPE) // TYPE表示用于类、接口、枚举、注解
@Retention(RetentionPolicy.RUNTIME) // 运行时加载到jvm中 有效范围最大
public @interface Aspect {
    /**
     * 用注解当标记  比如 @Aspect(Controller.class)就是拦截被@Controller标记的所有方法
     * // @interface关键字隐含的意思是继承了java.lang.annotation.Annotation接口
     */
    Class<? extends Annotation> value();
}
