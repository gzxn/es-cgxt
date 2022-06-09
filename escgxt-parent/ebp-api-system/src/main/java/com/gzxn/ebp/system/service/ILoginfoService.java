package com.gzxn.ebp.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gzxn.ebp.system.entity.Loginfo;
import com.gzxn.ebp.system.vo.LoginfoVo;

import java.io.Serializable;

/**
 * @Package: com.gzxn.ebp.system.service
 * @ClassName: ILoginfoService
 * @Author: CodeBird
 * @Date: 2022-03-06 18:12
 * @Description: 日志信息-服务类接口
 */
public interface ILoginfoService extends IService<Loginfo> {

    Page pageVo(Page page, QueryWrapper<LoginfoVo> queryWrapper);

    LoginfoVo getVoById(Serializable id);
}
