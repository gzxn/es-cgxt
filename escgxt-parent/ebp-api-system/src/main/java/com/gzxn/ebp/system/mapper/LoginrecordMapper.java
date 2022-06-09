package com.gzxn.ebp.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.ebp.system.entity.Loginrecord;
import com.gzxn.ebp.system.vo.LoginrecordVo;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * @Package: com.gzxn.ebp.system.mapper
 * @ClassName: LoginrecordMapper
 * @Author: CodeBird
 * @Date: 2022-03-06 18:01
 * @Description: 登录记录-Mapper
 */
public interface LoginrecordMapper extends BaseMapper<Loginrecord> {

    Page pageVo(Page page, @Param(Constants.WRAPPER) QueryWrapper<LoginrecordVo> queryWrapper);

    List<Integer> countYear(@Param("year") Integer year, @Param("uid") String uid);

    LoginrecordVo getVoById(Serializable id);
}
