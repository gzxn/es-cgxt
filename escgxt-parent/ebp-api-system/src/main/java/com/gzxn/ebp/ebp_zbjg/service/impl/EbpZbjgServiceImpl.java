package com.gzxn.ebp.ebp_zbjg.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzxn.ebp.ebp_cgb.entity.EbpCgb;
import com.gzxn.ebp.ebp_cgsq.entity.EbpCgsq;
import com.gzxn.ebp.ebp_cgsq.entity.EbpCgsqQd;
import com.gzxn.ebp.ebp_zbjg.entity.EbpZbjg;
import com.gzxn.ebp.ebp_zbjg.mapper.EbpZbjgMapper;
import com.gzxn.ebp.ebp_zbjg.service.IEbpZbjgService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Package: com.gzxn.ebp.ebp_zbjg.service.impl
 * @ClassName: EbpZbjgServiceImpl
 * @Description: 招标结果表-服务实现类
 * @author: CodeBird
 * @date: 2022-03-26 14:02:01
 */
@Service
public class EbpZbjgServiceImpl extends ServiceImpl<EbpZbjgMapper, EbpZbjg> implements IEbpZbjgService {

    @Override
    public Page ydjPageVo(Page page, Wrapper<EbpZbjg> wrapper) {
        return this.baseMapper.selectYdjPageVo(page, wrapper);
    }

    @Override
    public boolean saveData(Map<String, Object> record) {
        // JSON数据转实体类数据
        EbpCgsq ebpCgsq = JSON.toJavaObject((JSON) record.get("cgsqParameter"), EbpCgsq.class);
        EbpCgsqQd ebpCgsqQd = JSON.toJavaObject((JSON) record.get("cgsqqdParameter"), EbpCgsqQd.class);
        EbpCgb ebpCgb = JSON.toJavaObject((JSON) record.get("cgbParameter"), EbpCgb.class);
        EbpZbjg ebpZbjg = JSON.toJavaObject((JSON) record.get("zbjgParameter"), EbpZbjg.class);
        // 获取登录用户
        /*HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String uid = (String) request.getAttribute(Constant.REQUEST_ATTRIBUTE_UID);
        User user = iUserService.getById(uid);*/
        boolean flag = false;
        // 需要保存的任务数据
        ebpZbjg.setSbid(ebpCgsqQd.getId());
        ebpZbjg.setCgbid(ebpCgb.getId());
        ebpZbjg.setCgrwid(ebpCgb.getCgrwid());
        if (StringUtils.isEmpty(ebpZbjg.getCjjg())) {
            BigDecimal cjjg = new BigDecimal("0.00");
            ebpZbjg.setCjjg(cjjg);
        }
        if (this.save(ebpZbjg)) {
            flag = true;
        }
        return flag;
    }
}
