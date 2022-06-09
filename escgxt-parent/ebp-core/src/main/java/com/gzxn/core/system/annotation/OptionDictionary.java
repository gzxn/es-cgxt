package com.gzxn.core.system.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Package: com.gzxn.core.system.annotation
 * @ClassName: OptionDictionary
 * @Author: CodeBird
 * @Date: 2022-03-03 10:09
 * @Description: 选项字段-注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OptionDictionary {
    /**
     * 数据字典key
     *
     * @return 返回类型： String
     */
    String key();

    /**
     * 返回属性名称
     *
     * @return 返回类型： String
     */
    String fieldName() default "";

}
