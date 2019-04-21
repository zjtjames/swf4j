package com.swf4j.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 控制器注解 类比Spring的@Controller
 */
@Target(ElementType.TYPE) // 适用于类，接口
@Retention(RetentionPolicy.RUNTIME) // 运行时加载到jvm中 有效范围最大
public @interface Controller {
}
