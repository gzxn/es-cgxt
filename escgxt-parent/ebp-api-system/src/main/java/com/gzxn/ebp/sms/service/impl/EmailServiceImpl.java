package com.gzxn.ebp.sms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gzxn.core.system.api.ISystemCommonApi;
import com.gzxn.ebp.sms.dto.EmailSmsDto;
import com.gzxn.ebp.sms.entity.Templates;
import com.gzxn.ebp.sms.service.IEmailService;
import com.gzxn.ebp.sms.service.ITemplatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Package: com.gzxn.ebp.sms.service.impl
 * @ClassName: EmailServiceImpl
 * @Author: CodeBird
 * @Date: 2022-03-06 19:15
 * @Description: 邮件信息-服务实现类
 */
@Service
public class EmailServiceImpl implements IEmailService {
    @Autowired
    private ISystemCommonApi iSystemCommonApi;
    @Autowired
    private ITemplatesService iTemplatesService;

    @Override
    public void sendEmail(EmailSmsDto emailSmsDto) {
        // 判断是否模版解释
        if (emailSmsDto.getSendType() == 2) {
            // 模版标识查重
            Templates templates = iTemplatesService.getOne(new QueryWrapper<Templates>().eq("type", "2").eq("code", emailSmsDto.getTcode()));
            emailSmsDto.setContent(StrUtil.format(templates.getContent(), JSON.parseObject(emailSmsDto.getParameter()).getInnerMap()));
        }
        iSystemCommonApi.sendEmail(emailSmsDto.getTo(), emailSmsDto.getSubject(), emailSmsDto.getContent(), true);
    }

}