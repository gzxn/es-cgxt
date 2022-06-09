package com.gzxn.ebp.ebp_cgb.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzxn.ebp.ebp_cgb.entity.EbpCgb;
import com.gzxn.ebp.ebp_cgb.mapper.EbpCgbMapper;
import com.gzxn.ebp.ebp_cgb.service.IEbpCgbService;
import com.gzxn.ebp.ebp_cgsq.entity.EbpCgsq;
import com.gzxn.ebp.ebp_cgsq.entity.EbpCgsqQd;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @Package: com.gzxn.ebp.ebp_cgb.service.impl
 * @ClassName: EbpCgbServiceImpl
 * @Description: 采购包表-服务实现类
 * @author: CodeBird
 * @date: 2022-03-26 13:59:18
 */
@Service
public class EbpCgbServiceImpl extends ServiceImpl<EbpCgbMapper, EbpCgb> implements IEbpCgbService {

    @Override
    public Page ydbPageVo(Page page, Wrapper<EbpCgb> wrapper) {
        return this.baseMapper.selectYdbPageVo(page, wrapper);
    }

    @Override
    public EbpCgsq selectCgsqByCgbid(String id) {
        return this.baseMapper.selectCgsqByCgbid(id);
    }

    @Override
    public List<EbpCgsqQd> selectCgsqqdByCgbid(String id) {
        return this.baseMapper.selectCgsqqdByCgbid(id);
    }
}
