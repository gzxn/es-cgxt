package com.gzxn.config.responseaop;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import com.gzxn.core.exception.EbpRunTimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Package: com.gzxn.config.responseaop
 * @ClassName: ResponseAnnotationAdvice
 * @Author: CodeBird
 * @Date: 2022-03-06 15:17
 * @Description: 响应注解
 */
@ControllerAdvice
@Slf4j
public class ResponseAnnotationAdvice implements ResponseBodyAdvice {
    @Autowired
    private List<IResponseAnnotationHandler> iResponseAnnotationHandlers;

    /**
     * 是否需要拦截
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    /**
     * 处理返回值
     */
    @Override
    public Object beforeBodyWrite(Object returnObject, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (ObjectUtil.isEmpty(iResponseAnnotationHandlers)) {
            EbpRunTimeException ebpRunTimeException = new EbpRunTimeException("AnnotationAspect 注入入库组件出错。");
            log.error("异常：" + ebpRunTimeException.getMessage(), ebpRunTimeException);
            throw ebpRunTimeException;
        }
        // 执行Controller方法，并且获取返回类型
        // 执行注解处理器
        returnObject = getReturnObject(returnObject, iResponseAnnotationHandlers);
        return returnObject;
    }

    /**
     * 注解处理方法
     *
     * @param returnObject                aop处理器返回值
     * @param iResponseAnnotationHandlers 处理器执行链
     * @return
     */
    public Object getReturnObject(Object returnObject, List<IResponseAnnotationHandler> iResponseAnnotationHandlers) {
        // 判断是否对象，是对象执行注解解析
        if (ObjectUtil.isNotEmpty(returnObject) && filterType(returnObject)) {
            // 判断是否数组，是数组迭代数组内容
            if (returnObject instanceof Collection) {
                Collection collection = (Collection) returnObject;
                Collection returnCollection = null;
                if (returnObject instanceof List) {
                    returnCollection = new ArrayList();
                } else if (returnObject instanceof Set) {
                    returnCollection = new HashSet();
                }
                // 判断数组是否为空，如果为空就不需执行注解解析
                if (ObjectUtil.isNotEmpty(collection)) {
                    for (Object collectionObject : collection) {
                        // 执行对象转换代理对象
                        collectionObject = getReturnObject(collectionObject, iResponseAnnotationHandlers);
                        // 无法直接改变引用，创建一个新数组重新赋值
                        returnCollection.add(collectionObject);
                    }
                    return returnCollection;
                }
            } else if (returnObject instanceof Map) {
                Map map = (Map) returnObject;
                Map returnMap = new HashMap();
                // 判断数组是否为空，如果为空就不需执行注解解析
                if (ObjectUtil.isNotEmpty(map)) {
                    for (Object mapkey : map.keySet()) {
                        // 执行对象转换代理对象
                        Object mapValue = getReturnObject(map.get(mapkey), iResponseAnnotationHandlers);
                        // 无法直接改变引用，创建一个新数组重新赋值
                        returnMap.put(mapkey, mapValue);
                    }
                    return returnMap;
                }
            } else {
                // 获取对象属性
                Field[] returnObjectFields = ReflectUtil.getFieldsDirectly(returnObject.getClass(), true);
                // 查看对象属性是否object
                for (Field field : returnObjectFields) {
                    Object fieldValue = ReflectUtil.getFieldValue(returnObject, field);
                    if (filterType(fieldValue)) {
                        // 是object就递归查看是否还有object的属性
                        fieldValue = getReturnObject(fieldValue, iResponseAnnotationHandlers);
                        // 无法直接改变引用，使用反射把值设置到对象中
                        ReflectUtil.setFieldValue(returnObject, field, fieldValue);
                    }
                }
                // 执行链执行处理器
                for (IResponseAnnotationHandler iResponseAnnotationHandler : iResponseAnnotationHandlers) {
                    // 过滤object是否存在注解
                    List<Field> returnObjectFieldList = Arrays.asList(returnObjectFields);
                    List<Field> fieldSets = returnObjectFieldList.stream().filter(objectField -> {
                        Annotation annotation = objectField
                                .getAnnotation(iResponseAnnotationHandler.getAnnotationClass());
                        return ObjectUtil.isNotEmpty(annotation);
                    }).collect(Collectors.toList());
                    // object中有注解执行处理器
                    if (ObjectUtil.isNotEmpty(fieldSets)) {
                        returnObject = iResponseAnnotationHandler.handle(returnObject, fieldSets);
                    }
                }
                return returnObject;

            }
        }
        return returnObject;
    }

    /**
     * 过滤类型
     *
     * @param object
     * @return
     */
    private boolean filterType(Object object) {
        return !(object instanceof Number || object instanceof Boolean || object instanceof Character
                || object instanceof String || object instanceof Date || object instanceof Log
                || object instanceof BigDecimal);
    }

}
