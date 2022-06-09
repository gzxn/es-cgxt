package com.gzxn.ebp.ebp_cgrw.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzxn.core.constant.Constant;
import com.gzxn.ebp.ebp_cgrw.entity.EbpCgrw;
import com.gzxn.ebp.ebp_cgrw.mapper.EbpCgrwMapper;
import com.gzxn.ebp.ebp_cgrw.service.IEbpCgrwService;
import com.gzxn.ebp.ebp_cgsq.entity.EbpCgsq;
import com.gzxn.ebp.system.entity.User;
import com.gzxn.ebp.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.gzxn.ebp.ebp_cgrw.service.impl
 * @ClassName: EbpCgrwServiceImpl
 * @Description: 采购任务表-服务实现类
 * @author: CodeBird
 * @date: 2022-03-26 14:01:01
 */
@Service
public class EbpCgrwServiceImpl extends ServiceImpl<EbpCgrwMapper, EbpCgrw> implements IEbpCgrwService {

    @Autowired
    private IUserService iUserService;

    public String getCgrwLsh() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String str = sdf.format(date);
//        String xmlx = cgbhMap.get("xmlx")+"";
        String cgrwCode = "CGRW" + str;
        QueryWrapper<EbpCgrw> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("cgrwbh");
        List<EbpCgrw> list = baseMapper.selectList(queryWrapper);
        int num = 1;
        if (list.size() > 0) {
            String cgrwbh = list.get(0).getCgrwbh().split("-")[1];
            Integer cgrwbhInt = Integer.parseInt(cgrwbh);
            num = cgrwbhInt + 1;
        }
        if (num < 10) {
            cgrwCode = cgrwCode + "-00" + num;
        } else if (num >= 10 && num < 100) {
            cgrwCode = cgrwCode + "-0" + num;
        } else {
            cgrwCode = cgrwCode + "-" + num;
        }
        return cgrwCode;
    }

    @Override
    public boolean saveData(Map<String, Object> record) {
        // JSON数据转实体类数据
        EbpCgsq ebpCgsq = JSON.toJavaObject((JSON) record.get("cgsqParameter"), EbpCgsq.class);
        EbpCgrw ebpCgrw = JSON.toJavaObject((JSON) record.get("cgrwParameter"), EbpCgrw.class);
        // 获取登录用户
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String uid = (String) request.getAttribute(Constant.REQUEST_ATTRIBUTE_UID);
        User user = iUserService.getById(uid);
        boolean flag = false;
        // 需要保存的任务数据
        ebpCgrw.setCgrwbh(this.getCgrwLsh());
        ebpCgrw.setYsje(ebpCgsq.getYsje());
        ebpCgrw.setCgzxr(uid);
        ebpCgrw.setCgzxryhm(user.getName());
        ebpCgrw.setCgsqid(ebpCgsq.getId());
        if (this.save(ebpCgrw)) {
            flag = true;
        }
        return flag;
    }

    @Override
    public Page yfpPageVo(Page page, Wrapper<EbpCgrw> wrapper) {
        return this.baseMapper.selectYfpPageVo(page, wrapper);
    }

    @Override
    public Page yfpWdbPageVo(Page page, Wrapper<EbpCgrw> wrapper) {
        return this.baseMapper.selectYfpWdbPageVo(page, wrapper);
    }
}
