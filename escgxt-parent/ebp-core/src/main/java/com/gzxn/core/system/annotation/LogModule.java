package com.gzxn.core.system.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Package: com.gzxn.core.system.annotation
 * @ClassName: LogModule
 * @Author: CodeBird
 * @Date: 2022-03-03 09:57
 * @Description: 日志模块-注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogModule {
    String value();
}