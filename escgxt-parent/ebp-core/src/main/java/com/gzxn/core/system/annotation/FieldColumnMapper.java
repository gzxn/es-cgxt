package com.gzxn.core.system.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Package: com.gzxn.core.system.annotation
 * @ClassName: FieldColumnMapper
 * @Author: CodeBird
 * @Date: 2022-03-03 09:55
 * @Description: 字段列-注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldColumnMapper {
    /**
     * 数据表名
     *
     * @return 返回类型： String
     */
    String tableName();

    /**
     * 返回字段名
     *
     * @return 返回类型： String
     */
    String columnName();

    /**
     * 查询字段名
     *
     * @return 返回类型： String
     */
    String whereName();

    /**
     * 返回属性名称
     *
     * @return 返回类型： String
     */
    String fieldName() default "";

}
