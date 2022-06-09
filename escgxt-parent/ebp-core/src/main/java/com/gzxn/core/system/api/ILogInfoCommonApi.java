package com.gzxn.core.system.api;

import com.gzxn.core.system.dto.LoginfoDto;

/**
 * @Package: com.gzxn.core.system.api
 * @ClassName: ILogInfoCommonApi
 * @Author: CodeBird
 * @Date: 2022-03-06 12:15
 * @Description: 日志公共业务接口
 */
public interface ILogInfoCommonApi {

    void saveLog(LoginfoDto loginfoDto);

}

