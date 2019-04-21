package com.swf4j.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 服务类注解 类比Spring的@Service
 */
@Target(ElementType.TYPE) // 适用于类，接口
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
}
