package com.gzxn.core.base.controller;

import cn.hutool.extra.servlet.ServletUtil;
import com.gzxn.core.system.api.ISystemCommonApi;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: BaseController
 * @Author: CodeBird
 * @Date: 2022-03-03 21:21
 * @Description:
 */
public class BaseController {

    /**
     * 获取Ip
     *
     * @param request
     * @return
     */
    protected String getIp(HttpServletRequest request) {
        return ServletUtil.getClientIP(request);
    }

    /**
     * 获取token
     *
     * @param request
     * @return
     */
    protected String getToken(HttpServletRequest request) {
        return ISystemCommonApi.getRequestToken(request);
    }

}
