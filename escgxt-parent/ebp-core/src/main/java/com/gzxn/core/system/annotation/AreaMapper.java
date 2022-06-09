package com.gzxn.core.system.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Package: com.gzxn.core.system.annotation
 * @ClassName: AreaMapper
 * @Author: CodeBird
 * @Date: 2022-03-06 04:57
 * @Description: 地区字段-注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AreaMapper {

    /**
     * 返回属性名称
     *
     * @return 返回类型： String
     */
    String fieldName() default "";

    /**
     * 返回地区表的字段名称
     *
     * @return 返回类型： String
     */
    String columnName() default "name";

}
