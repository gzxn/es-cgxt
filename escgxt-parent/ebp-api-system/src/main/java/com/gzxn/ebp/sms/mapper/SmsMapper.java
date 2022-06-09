package com.gzxn.ebp.sms.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.ebp.sms.entity.Sms;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Package: com.gzxn.ebp.sms.mapper
 * @ClassName: SmsMapper
 * @Author: CodeBird
 * @Date: 2022-03-06 19:12
 * @Description: 站内信息表-Mapper
 */
public interface SmsMapper extends BaseMapper<Sms> {

    Page selectPageVo(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

    Sms getVoById(@PathVariable("id") String id);
}
