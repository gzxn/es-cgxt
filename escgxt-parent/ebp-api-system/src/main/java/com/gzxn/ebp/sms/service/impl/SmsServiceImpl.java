package com.gzxn.ebp.sms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzxn.config.sms.SmsServer;
import com.gzxn.core.system.api.ISystemCommonApi;
import com.gzxn.ebp.sms.dto.SendSmsDto;
import com.gzxn.ebp.sms.entity.Sms;
import com.gzxn.ebp.sms.entity.Templates;
import com.gzxn.ebp.sms.mapper.SmsMapper;
import com.gzxn.ebp.sms.service.ISmsService;
import com.gzxn.ebp.sms.service.ITemplatesService;
import com.gzxn.ebp.system.entity.User;
import com.gzxn.ebp.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Package: com.gzxn.ebp.sms.service.impl
 * @ClassName: SmsServiceImpl
 * @Author: CodeBird
 * @Date: 2022-03-06 19:16
 * @Description: 站内信息表-服务实现类
 */
@Service
public class SmsServiceImpl extends ServiceImpl<SmsMapper, Sms> implements ISmsService {

    @Autowired
    private SmsServer smsServer;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ISystemCommonApi iSystemCommonApi;
    @Autowired
    private ITemplatesService iTemplatesService;

    @Override
    public Page pageVo(Page page, Wrapper wrapper) {
        return this.baseMapper.selectPageVo(page, wrapper);
    }

    @Override
    public Sms getVoById(String id) {
        return this.baseMapper.getVoById(id);
    }

    @Override
    public Boolean sendSms(SendSmsDto sendSmsDto) {

        // 判断是否模版解释
        if (sendSmsDto.getSendType() == 2) {
            Templates templates = iTemplatesService.getOne(new QueryWrapper<Templates>().eq("type", "1").eq("code", sendSmsDto.getTcode()));
            sendSmsDto.setContent(StrUtil.format(templates.getContent(), JSON.parseObject(sendSmsDto.getParameter()).getInnerMap()));
        }

        boolean result = false;
        // 阅读状态:1-未读、2-已读
        sendSmsDto.setRstate(1);
        // 发送时间
        sendSmsDto.setStime(new Date());
        // 发送类型 1-广播、2-个人
        if (StrUtil.equals(sendSmsDto.getStype(), "1")) {
            // 1-广播
            // 是否禁用：1-是、2-否
            List<User> users = iUserService.list(new QueryWrapper<User>().eq("isdisable", 2));
            if (ObjectUtil.isNotEmpty(users)) {
                List<Sms> smss = new ArrayList();
                for (User user : users) {
                    Sms sms = BeanUtil.copyProperties(sendSmsDto, Sms.class, "stype", "rid");
                    sms.setRid(user.getId());
                    smss.add(sms);
                }
                result = this.saveBatch(smss);
                if (result) {
                    for (Sms sms : smss) {
                        iSystemCommonApi.notifyUpdateSms(sms.getRid());
                    }
                }
            }
        } else {
            // 2-个人
            Sms sms = BeanUtil.copyProperties(sendSmsDto, Sms.class, "stype");

            result = this.save(sms);
            if (result) {
                iSystemCommonApi.notifyUpdateSms(sms.getRid());
            }
        }
        return result;
    }

    @Override
    public Boolean readBatch(Collection<String> idList) {
        List<Sms> smss = idList.stream().map((id) -> {
            Sms sms = this.getById(id);
            // 阅读状态:1-未读、2-已读
            sms.setRstate(2);
            sms.setRtime(new Date());
            return sms;
        }).collect(Collectors.toList());
        boolean result = this.updateBatchById(smss);
        if (ObjectUtil.isNotEmpty(smss)) {
            for (Sms sms : smss) {
                iSystemCommonApi.notifyUpdateSms(sms.getRid());
            }
        }
        return result;
    }
}