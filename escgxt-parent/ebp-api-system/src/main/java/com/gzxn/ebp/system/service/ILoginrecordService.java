package com.gzxn.ebp.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gzxn.ebp.system.entity.Loginrecord;
import com.gzxn.ebp.system.vo.LoginrecordVo;

import java.io.Serializable;
import java.util.List;

/**
 * @Package: com.gzxn.ebp.system.service
 * @ClassName: ILoginrecordService
 * @Author: CodeBird
 * @Date: 2022-03-06 18:12
 * @Description: 登录记录-服务类接口
 */
public interface ILoginrecordService extends IService<Loginrecord> {
    Page pageVo(Page page, QueryWrapper<LoginrecordVo> queryWrapper);

    List<Integer> countYear(Integer year, String uid);

    LoginrecordVo getVoById(Serializable id);

}