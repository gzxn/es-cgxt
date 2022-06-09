package com.gzxn.ebp.ebp_cgsq.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzxn.ebp.ebp_cgsq.entity.EbpCgsq;
import com.gzxn.ebp.ebp_cgsq.entity.EbpCgsqQd;
import com.gzxn.ebp.ebp_cgsq.mapper.EbpCgsqMapper;
import com.gzxn.ebp.ebp_cgsq.service.IEbpCgsqQdService;
import com.gzxn.ebp.ebp_cgsq.service.IEbpCgsqService;
import com.gzxn.ebp.ebp_jfly.entity.EbpJfsy;
import com.gzxn.ebp.ebp_jfly.service.IEbpJfsyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Copyright  2022-03-26 QLH. Tech Ltd. All rights reserved.
 *
 * @Package: com.gzxn.ebp.ebp_cgsq.service.impl
 * @ClassName: EbpCgsqServiceImpl
 * @Description: 采购申请表-服务实现类
 * @author: CodeBird
 * @date: 2022-03-26 13:57:47
 */
@Service
public class EbpCgsqServiceImpl extends ServiceImpl<EbpCgsqMapper, EbpCgsq> implements IEbpCgsqService {
    @Autowired
    private IEbpCgsqQdService iEbpCgsqQdService;
    @Autowired
    private IEbpJfsyService iEbpJfsyService;

    @Override
    public Page pageVo(Page page, Wrapper wrapper) {
        return this.baseMapper.selectPageVo(page, wrapper);
    }

    @Override
    public Page wtjPageVo(Page page, Wrapper wrapper) {
        return this.baseMapper.selectWtjPageVo(page, wrapper);
    }

    @Override
    public Page ytjPageVo(Page page, Wrapper wrapper) {
        return this.baseMapper.selectYtjPageVo(page, wrapper);
    }

    @Override
    public Page wshPageVo(Page page, Wrapper wrapper) {
        return this.baseMapper.selectWshPageVo(page, wrapper);
    }

    @Override
    public Page yshPageVo(Page page, Wrapper wrapper) {
        return this.baseMapper.selectYshPageVo(page, wrapper);
    }

    @Override
    public Page yshtgPageVo(Page page, Wrapper wrapper) {
        return this.baseMapper.selectYshtgPageVo(page, wrapper);
    }

    @Override
    public String getCgsqLsh() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String str = sdf.format(date);
//        String xmlx = cgbhMap.get("xmlx")+"";
        String cgsqCode = "CGSQ" + str;
        QueryWrapper<EbpCgsq> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("cgbh");
        List<EbpCgsq> list = baseMapper.selectList(queryWrapper);
        int num = 1;
        if (list.size() > 0) {
            String cgbh = list.get(0).getCgbh().split("-")[1];
            Integer cgbhInt = Integer.parseInt(cgbh);
            num = cgbhInt + 1;
        }
        if (num < 10) {
            cgsqCode = cgsqCode + "-00" + num;
        } else if (num >= 10 && num < 100) {
            cgsqCode = cgsqCode + "-0" + num;
        } else {
            cgsqCode = cgsqCode + "-" + num;
        }

        return cgsqCode;
    }

    @Override
    public String getCgsqqdLsh(String cgbh) {
        String cgsqCode = cgbh + "-" + "001";
        return cgsqCode;
    }

    @Override
    public boolean saveData(Map<String, Object> record) {
        EbpCgsq ebpCgsq = JSON.toJavaObject((JSON) record.get("cgsqParameter"), EbpCgsq.class);
        EbpCgsqQd ebpCgsqQd = JSON.toJavaObject((JSON) record.get("cgsqqdParameter"), EbpCgsqQd.class);
        EbpJfsy ebpJfsy = JSON.toJavaObject((JSON) record.get("jfsyParameter"), EbpJfsy.class);
        boolean flag = false;
        if (this.updateById(ebpCgsq)) {
            iEbpCgsqQdService.updateById(ebpCgsqQd);
            iEbpJfsyService.updateById(ebpJfsy);
            flag = true;
        }
        return flag;
    }
}
