package com.swf4j.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解需要事务控制的方法
 * 将该注解应用在每个具有事务性的方法上（增、删、改），查是只读性的，不需要放在事务里
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Transaction {
}
