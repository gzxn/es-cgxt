package com.gzxn.ebp.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzxn.ebp.system.entity.Loginfo;
import com.gzxn.ebp.system.mapper.LoginfoMapper;
import com.gzxn.ebp.system.service.ILoginfoService;
import com.gzxn.ebp.system.vo.LoginfoVo;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @Package: com.gzxn.ebp.system.service.impl
 * @ClassName: LoginfoServiceImpl
 * @Author: CodeBird
 * @Date: 2022-03-06 18:20
 * @Description: 日志信息-服务实现类
 */
@Service
public class LoginfoServiceImpl extends ServiceImpl<LoginfoMapper, Loginfo> implements ILoginfoService {

    @Override
    public Page pageVo(Page page, QueryWrapper<LoginfoVo> queryWrapper) {
        return baseMapper.pageVo(page, queryWrapper);
    }

    @Override
    public LoginfoVo getVoById(Serializable id) {
        return baseMapper.getVoById(id);
    }

}
