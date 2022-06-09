package com.gzxn.ebp.ebp_ysd.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzxn.ebp.ebp_ysd.entity.EbpYsd;
import com.gzxn.ebp.ebp_ysd.mapper.EbpYsdMapper;
import com.gzxn.ebp.ebp_ysd.service.IEbpYsdService;
import com.gzxn.ebp.ebp_zbjg.entity.EbpZbjg;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Package: com.gzxn.ebp.ebp_ysd.service.impl
 * @ClassName: EbpYsdServiceImpl
 * @Description: 验收单表-服务实现类
 * @author: CodeBird
 * @date: 2022-03-26 14:03:06
 */
@Service
public class EbpYsdServiceImpl extends ServiceImpl<EbpYsdMapper, EbpYsd> implements IEbpYsdService {

    @Override
    public Page ysdPageVo(Page page, Wrapper<EbpYsd> wrapper) {
        return this.baseMapper.selectYsdPageVo(page, wrapper);
    }

    @Override
    public boolean saveData(Map<String, Object> record) {
        // JSON数据转实体类数据
        EbpZbjg ebpZbjg = JSON.toJavaObject((JSON) record.get("zbjgParameter"), EbpZbjg.class);
        EbpYsd ebpYsd = JSON.toJavaObject((JSON) record.get("ysdParameter"), EbpYsd.class);
        // 获取登录用户
        /*HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String uid = (String) request.getAttribute(Constant.REQUEST_ATTRIBUTE_UID);
        User user = iUserService.getById(uid);*/
        boolean flag = false;
        // 需要保存的任务数据
        ebpYsd.setZbjgId(ebpZbjg.getId());
        if (this.save(ebpYsd)) {
            flag = true;
        }
        return flag;
    }
}
