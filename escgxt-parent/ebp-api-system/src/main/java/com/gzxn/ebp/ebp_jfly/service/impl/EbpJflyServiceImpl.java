package com.gzxn.ebp.ebp_jfly.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzxn.ebp.ebp_jfly.entity.EbpJfly;
import com.gzxn.ebp.ebp_jfly.mapper.EbpJflyMapper;
import com.gzxn.ebp.ebp_jfly.service.IEbpJflyService;
import com.gzxn.ebp.system.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package: com.gzxn.ebp.ebp_jfly.service.impl
 * @ClassName: EbpJflyServiceImpl
 * @Description: 经费来源表-服务实现类
 * @author: CodeBird
 * @date: 2022-03-26 14:03:37
 */
@Service
public class EbpJflyServiceImpl extends ServiceImpl<EbpJflyMapper, EbpJfly> implements IEbpJflyService {

    @Override
    public Page pageVo(Page page, Wrapper wrapper) {
        return this.baseMapper.selectPageVo(page, wrapper);
    }

    @Override
    public List<Department> departmentVo(String id) {
        return this.baseMapper.selectDepartmentById(id);
    }
}
