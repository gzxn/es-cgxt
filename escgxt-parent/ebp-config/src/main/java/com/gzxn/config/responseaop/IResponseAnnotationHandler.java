package com.gzxn.config.responseaop;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * @Package: com.gzxn.config.responseaop
 * @ClassName: IResponseAnnotationHandler
 * @Author: CodeBird
 * @Date: 2022-03-06 03:15
 * @Description: 响应注解处理器
 */
public interface IResponseAnnotationHandler {

    /**
     * 执行注解处理器的类型
     *
     * @return
     */
    Class getAnnotationClass();

    /**
     * 执行注解处理器实现
     *
     * @param object          返回值
     * @param returnTypeClass 返回值类型
     * @param fieldList       返回值类型和泛型类型属性列表
     * @return
     * @throws Throwable
     */
    Object handle(Object responseObject, Collection<Field> fieldSets);

}
