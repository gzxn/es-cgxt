package com.gzxn.ebp.sms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gzxn.ebp.sms.dto.SendSmsDto;
import com.gzxn.ebp.sms.entity.Sms;

import java.util.Collection;

/**
 * @Package: com.gzxn.ebp.sms.service
 * @ClassName: ISmsService
 * @Author: CodeBird
 * @Date: 2022-03-06 19:14
 * @Description: 站内信息表-服务类接口
 */
public interface ISmsService extends IService<Sms> {

    Boolean sendSms(SendSmsDto sendSmsDto);

    Boolean readBatch(Collection<String> idList);

    Page pageVo(Page page, Wrapper wrapper);

    Sms getVoById(String id);
}
