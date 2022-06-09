package com.gzxn.ebp.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.ebp.system.entity.Loginfo;
import com.gzxn.ebp.system.vo.LoginfoVo;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * @Package: com.gzxn.ebp.system.mapper
 * @ClassName: LoginfoMapper
 * @Author: CodeBird
 * @Date: 2022-03-06 18:01
 * @Description: 日志信息-Mapper
 */
public interface LoginfoMapper extends BaseMapper<Loginfo> {

    Page pageVo(Page page, @Param(Constants.WRAPPER) QueryWrapper<LoginfoVo> queryWrapper);

    LoginfoVo getVoById(Serializable id);
}