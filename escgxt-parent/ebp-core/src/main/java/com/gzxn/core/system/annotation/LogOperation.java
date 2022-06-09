package com.gzxn.core.system.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Package: com.gzxn.core.system.annotation
 * @ClassName: LogOperation
 * @Author: CodeBird
 * @Date: 2022-03-03 10:07
 * @Description: 日志操作-注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogOperation {
    String value();
}
