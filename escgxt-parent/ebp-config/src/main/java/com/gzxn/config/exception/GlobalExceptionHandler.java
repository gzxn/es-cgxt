package com.gzxn.config.exception;

import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSON;
import com.gzxn.core.constant.Constant;
import com.gzxn.core.vo.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Package: com.gzxn.config.exception
 * @ClassName: GlobalExceptionHandler
 * @Author: CodeBird
 * @Date: 2022-03-06 13:50
 * @Description: 全局异常处理
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AuthorizationException.class)
    public void authorizationExceptionHandler(AuthorizationException e) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        log.error("没有权限异常：" + e.getMessage(), e);
        ServletUtil.write(response, JSON.toJSONString(new ResponseBean(Constant.RESPONSE_CODE_NOAUTH, Constant.RESPONSE_MSG_NOAUTH, null)), "application/json;charset=UTF-8");
    }
}
