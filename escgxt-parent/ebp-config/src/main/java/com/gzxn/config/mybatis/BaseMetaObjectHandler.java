package com.gzxn.config.mybatis;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.gzxn.core.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Package: com.gzxn.config.mybatis
 * @ClassName: BaseMetaObjectHandler
 * @Author: CodeBird
 * @Date: 2022-03-06 14:29
 * @Description: 公共字段填充
 */
@Slf4j
@Component
public class BaseMetaObjectHandler implements MetaObjectHandler {

    /**
     * 新增填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Date date = new Date();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String uid = (String) request.getAttribute(Constant.REQUEST_ATTRIBUTE_UID);
        if (!StrUtil.isBlank(uid)) {
            // 创建人
            setFieldValByName("crer", uid, metaObject);
            // 修改人
            setFieldValByName("mder", uid, metaObject);
        }

        // 创建时间
        setFieldValByName("crtm", date, metaObject);
        // 修改时间
        setFieldValByName("mdtm", date, metaObject);
    }

    /**
     * 更新填充
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        Date date = new Date();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String uid = (String) request.getAttribute(Constant.REQUEST_ATTRIBUTE_UID);
        if (!StrUtil.isBlank(uid)) {
            // 修改人
            setFieldValByName("mder", uid, metaObject);
        }
        // 修改时间
        setFieldValByName("mdtm", date, metaObject);
    }

}
