package com.gzxn.ebp.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzxn.ebp.system.entity.Loginrecord;
import com.gzxn.ebp.system.mapper.LoginrecordMapper;
import com.gzxn.ebp.system.service.ILoginrecordService;
import com.gzxn.ebp.system.vo.LoginrecordVo;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @Package: com.gzxn.ebp.system.service.impl
 * @ClassName: LoginrecordServiceImpl
 * @Author: CodeBird
 * @Date: 2022-03-06 18:21
 * @Description: 登录记录-服务实现类
 */
@Service
public class LoginrecordServiceImpl extends ServiceImpl<LoginrecordMapper, Loginrecord> implements ILoginrecordService {

    @Override
    public Page pageVo(Page page, QueryWrapper<LoginrecordVo> queryWrapper) {
        return baseMapper.pageVo(page, queryWrapper);
    }

    @Override
    public List<Integer> countYear(Integer year, String uid) {
        return baseMapper.countYear(year,uid);
    }

    @Override
    public LoginrecordVo getVoById(Serializable id) {
        return baseMapper.getVoById(id);
    }

}
