package com.gzxn.config.loginfo;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.gzxn.core.constant.Constant;
import com.gzxn.core.exception.EbpException;
import com.gzxn.core.exception.EbpRunTimeException;
import com.gzxn.core.system.annotation.LogModule;
import com.gzxn.core.system.annotation.LogOperation;
import com.gzxn.core.system.api.ILogInfoCommonApi;
import com.gzxn.core.system.dto.LoginfoDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Package: com.gzxn.config.loginfo
 * @ClassName: LogInfoAspect
 * @Author: CodeBird
 * @Date: 2022-03-06 14:10
 * @Description: 日志操作Aop
 */
@Aspect
@Component
@Slf4j
public class LogInfoAspect {
    @Autowired
    private ILogInfoCommonApi iLogInfoCommonApi;

    @Around("@within(com.gzxn.core.system.annotation.LogModule) and @annotation(com.gzxn.core.system.annotation.LogOperation)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if (ObjectUtil.isEmpty(iLogInfoCommonApi)) {
            EbpException ebpException = new EbpException("LogInfoAspect 注入入库组件出错。");
            log.error("异常：" + ebpException.getMessage(), ebpException);
            throw ebpException;
        }
        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature msig = null;
        if (!(signature instanceof MethodSignature)) {
            throw new EbpRunTimeException("该注解只能用于方法");
        }
        msig = (MethodSignature) signature;
        Object target = proceedingJoinPoint.getTarget();
        Class targetClass = target.getClass();
        // 获取类注解
        LogModule logModule = (LogModule) targetClass.getAnnotation(LogModule.class);
        // 尝试获取方法
        Method currentMethod = targetClass.getMethod(msig.getName(), msig.getParameterTypes());
        // 获取方法的注解
        LogOperation logOperation = currentMethod.getAnnotation(LogOperation.class);
        // 获取请求
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        // IP
        String ip = ServletUtil.getClientIP(request);
        // UserAgent
        String userAgent = ServletUtil.getHeader(request, "User-Agent", "UTF-8");
        // uid
        String uid = (String) request.getAttribute(Constant.REQUEST_ATTRIBUTE_UID);

        LoginfoDto loginfoDto = new LoginfoDto();
        loginfoDto.setIp(ip);
        loginfoDto.setUseragent(userAgent);
        loginfoDto.setOptime(new Date());
        loginfoDto.setUid(uid);
        loginfoDto.setModule(logModule.value());
        loginfoDto.setUri(request.getRequestURI());
        loginfoDto.setOperation(logOperation.value());

        Object[] args = proceedingJoinPoint.getArgs();
        Stream<?> stream = ArrayUtils.isEmpty(args) ? Stream.empty() : Arrays.asList(args).stream();
        List<Object> logArgs = stream
                .filter(arg -> (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse)))
                .collect(Collectors.toList());
        // 过滤后序列化无异常
        String string = JSON.toJSONString(logArgs);
        loginfoDto.setContent(string);
        // 日志类型 1-业务、2-错误信息
        loginfoDto.setType(1);
        iLogInfoCommonApi.saveLog(loginfoDto);
        Object result = proceedingJoinPoint.proceed();
        return result;
    }

    @AfterThrowing(value = "@within(com.gzxn.core.system.annotation.LogModule) and @annotation(com.gzxn.core.system.annotation.LogOperation)", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Exception exception) throws Throwable {
        if (ObjectUtil.isEmpty(iLogInfoCommonApi)) {
            EbpException ebpException = new EbpException("LogInfoAspect 注入入库组件出错。");
            log.error("异常：" + ebpException.getMessage(), ebpException);
            throw ebpException;
        }
        Signature signature = joinPoint.getSignature();
        MethodSignature msig = null;
        if (!(signature instanceof MethodSignature)) {
            throw new EbpRunTimeException("该注解只能用于方法");
        }
        msig = (MethodSignature) signature;
        Object target = joinPoint.getTarget();
        Class targetClass = target.getClass();
        // 获取类注解
        LogModule logModule = (LogModule) targetClass.getAnnotation(LogModule.class);
        // 尝试获取方法
        Method currentMethod = targetClass.getMethod(msig.getName(), msig.getParameterTypes());
        // 获取方法的注解
        LogOperation logOperation = currentMethod.getAnnotation(LogOperation.class);
        // 获取请求
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        // IP
        String ip = ServletUtil.getClientIP(request);
        // UserAgent
        String userAgent = ServletUtil.getHeader(request, "User-Agent", "UTF-8");
        // uid
        String uid = (String) request.getAttribute(Constant.REQUEST_ATTRIBUTE_UID);
        LoginfoDto loginfoDto = new LoginfoDto();
        loginfoDto.setIp(ip);
        loginfoDto.setUseragent(userAgent);
        loginfoDto.setOptime(new Date());
        loginfoDto.setUid(uid);
        loginfoDto.setModule(logModule.value());
        loginfoDto.setUri(request.getRequestURI());
        loginfoDto.setOperation(logOperation.value());
        loginfoDto.setContent(exception.toString());
        // 日志类型 1-业务、2-错误信息
        loginfoDto.setType(2);
        iLogInfoCommonApi.saveLog(loginfoDto);
    }

}