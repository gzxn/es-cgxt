package com.gzxn.config.responseaop;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import com.gzxn.config.utils.DynamicBean;
import com.gzxn.core.exception.EbpRunTimeException;
import com.gzxn.core.system.annotation.FieldColumnMapper;
import com.gzxn.core.system.api.IAreaCommonApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Package: com.gzxn.config.responseaop
 * @ClassName: FieldColumnMapperHandler
 * @Author: CodeBird
 * @Date: 2022-03-06 15:14
 * @Description: 响应属性字段映射处理器
 */
@Component
@Order(3)
@Slf4j
public class FieldColumnMapperHandler implements IResponseAnnotationHandler {
    @Autowired
    private IAreaCommonApi iAreaCommonApi;
    /**
     * 返回前缀
     */
    private static final String FIELDCOLUMN_PREFIX = "fieldColumn_";

    @Override
    public Object handle(Object responseObject, Collection<Field> fieldSets) {
        if (ObjectUtil.isEmpty(iAreaCommonApi)) {
            EbpRunTimeException ebpRunTimeException = new EbpRunTimeException("FieldColumnMapperHandler 注入入库组件出错。");
            log.error("异常：" + ebpRunTimeException.getMessage(), ebpRunTimeException);
            throw ebpRunTimeException;
        }

        // 注解属性，解析后属性存储map
        Map<String, Object> addField = new HashMap<String, Object>();
        for (Field setField : fieldSets) {
            // 获取属性注解
            FieldColumnMapper fieldColumnMapper = setField.getAnnotation(FieldColumnMapper.class);

            // 数据表名
            String fieldColumnMapperTableName = fieldColumnMapper.tableName();
            // 返回字段名
            String fieldColumnMapperColumnName = fieldColumnMapper.columnName();
            // 查询字段名
            String fieldColumnMapperWhereName = fieldColumnMapper.whereName();
            // 返回属性名称
            String fieldColumnMapperFieldName = fieldColumnMapper.fieldName();

            // 返回字段值
            Object annotationColumnValue = null;

            // 判断是否自定义返回属性名
            if (ObjectUtil.isEmpty(fieldColumnMapperFieldName)) {
                fieldColumnMapperFieldName = FIELDCOLUMN_PREFIX + setField.getName();
            } else {
                fieldColumnMapperFieldName = FIELDCOLUMN_PREFIX + fieldColumnMapperFieldName;
            }
            // 获取属性值
            Object fieldValue = ReflectUtil.getFieldValue(responseObject, setField.getName());
            if (ObjectUtil.isEmpty(fieldValue)) {
                // 转换为代理类后，属性前加上了"$cglib_prop_"，普通属性名获取为空是再获取一次代理属性值
                fieldValue = ReflectUtil.getFieldValue(responseObject, "$cglib_prop_" + setField.getName());
            }
            if (ObjectUtil.isEmpty(fieldValue)) {
                log.warn("class:" + setField.getDeclaringClass() + ",field:" + setField.getName() + ",fieldValue:"
                        + fieldValue + "。数据表字段属性值为null。");
                annotationColumnValue = "";
            } else {

                Object columnByField = iAreaCommonApi.getColumnByField(fieldColumnMapperTableName,
                        fieldColumnMapperColumnName, fieldColumnMapperWhereName, fieldValue);

                // 判断属性值在数据库是否有数据，有数据匹配成功，没有返回原值
                if (ObjectUtil.isEmpty(columnByField)) {
                    log.warn("class:" + setField.getDeclaringClass() + ",field:" + setField.getName() + ",fieldValue:"
                            + fieldValue + "。数据表字段映射项失败。");
                    annotationColumnValue = fieldValue;
                } else {
                    annotationColumnValue = columnByField;
                }
            }
            // 设置追加解析属性
            addField.put(fieldColumnMapperFieldName, annotationColumnValue);
        }
        if (ObjectUtil.isNotEmpty(addField)) {
            // responseObject添加属性
            responseObject = DynamicBean.getTarget(responseObject, addField);
        }
        return responseObject;
    }

    @Override
    public Class getAnnotationClass() {
        return FieldColumnMapper.class;
    }

}
