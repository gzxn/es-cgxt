package com.gzxn.ebp.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.gzxn.core.system.api.ILogInfoCommonApi;
import com.gzxn.core.system.dto.LoginfoDto;
import com.gzxn.ebp.system.entity.Loginfo;
import com.gzxn.ebp.system.service.ILoginfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Package: com.gzxn.ebp.system.service.impl
 * @ClassName: LoginInfoCommonApiImpl
 * @Author: CodeBird
 * @Date: 2022-03-06 18:21
 * @Description: 登录信息公共Api
 */
@Service
@Slf4j
public class LoginInfoCommonApiImpl implements ILogInfoCommonApi {

    @Autowired
    private ILoginfoService iLoginfoService;

    @Override
    public void saveLog(LoginfoDto loginfoDto) {
        iLoginfoService.save(BeanUtil.copyProperties(loginfoDto, Loginfo.class));
    }

}