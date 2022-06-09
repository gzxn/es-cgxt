package com.gzxn.ebp.ebp_ht.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzxn.ebp.ebp_cgsq.entity.EbpCgsq;
import com.gzxn.ebp.ebp_cgsq.entity.EbpCgsqQd;
import com.gzxn.ebp.ebp_cgsq.service.IEbpCgsqQdService;
import com.gzxn.ebp.ebp_cgsq.service.IEbpCgsqService;
import com.gzxn.ebp.ebp_ht.entity.EbpHt;
import com.gzxn.ebp.ebp_ht.mapper.EbpHtMapper;
import com.gzxn.ebp.ebp_ht.service.IEbpHtService;
import com.gzxn.ebp.ebp_jfly.entity.EbpJfsy;
import com.gzxn.ebp.ebp_jfly.service.IEbpJfsyService;
import com.gzxn.ebp.ebp_zbjg.entity.EbpZbjg;
import com.gzxn.ebp.ebp_zbjg.service.IEbpZbjgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.gzxn.ebp.ebp_ht.service.impl
 * @ClassName: EbpHtServiceImpl
 * @Description: 合同表-服务实现类
 * @author: CodeBird
 * @date: 2022-03-26 14:02:50
 */
@Service
public class EbpHtServiceImpl extends ServiceImpl<EbpHtMapper, EbpHt> implements IEbpHtService {

    @Autowired
    private IEbpCgsqService iEbpCgsqService;
    @Autowired
    private IEbpCgsqQdService iEbpCgsqQdService;
    @Autowired
    private IEbpJfsyService iEbpJfsyService;
    @Autowired
    private IEbpZbjgService iEbpZbjgService;

    @Override
    public Page dngPageVo(Page page, Wrapper wrapper) {
        return this.baseMapper.selectDngPageVo(page, wrapper);
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
    public String getHtLsh() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String str = sdf.format(date);
        String htCode = "HT" + str;
        QueryWrapper<EbpHt> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("htbh");
        List<EbpHt> list = baseMapper.selectList(queryWrapper);
        int num = 1;
        if (list.size() > 0) {
            String htbh = list.get(0).getHtbh().split("-")[1];
            Integer cgbhInt = Integer.parseInt(htbh);
            num = cgbhInt + 1;
        }
        if (num < 10) {
            htCode = htCode + "-00" + num;
        } else if (num >= 10 && num < 100) {
            htCode = htCode + "-0" + num;
        } else {
            htCode = htCode + "-" + num;
        }
        return htCode;
    }

    @Override
    public boolean saveData(Map<String, Object> record) {
        EbpCgsq ebpCgsq = JSON.toJavaObject((JSON) record.get("cgsqParameter"), EbpCgsq.class);
        EbpCgsqQd ebpCgsqQd = JSON.toJavaObject((JSON) record.get("cgsqqdParameter"), EbpCgsqQd.class);
        EbpJfsy ebpJfsy = JSON.toJavaObject((JSON) record.get("jfsyParameter"), EbpJfsy.class);
        EbpZbjg ebpZbjg = JSON.toJavaObject((JSON) record.get("zbjgParameter"), EbpZbjg.class);
        EbpHt ebpHt = JSON.toJavaObject((JSON) record.get("htParameter"), EbpHt.class);
        boolean flag = false;
        if (this.updateById(ebpHt)) {
            flag = true;
        }
        return flag;
    }
}
