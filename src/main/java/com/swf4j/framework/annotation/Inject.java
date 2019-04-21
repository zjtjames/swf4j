package com.swf4j.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 依赖注入（DI）注解 类比Spring的@Autowired
 */
@Target(ElementType.FIELD) // 适用于成员变量
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
}
