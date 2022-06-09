package com.gzxn.ebp.system.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gzxn.config.redis.RedisUtil;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.constant.CacheConstant;
import com.gzxn.core.constant.Constant;
import com.gzxn.core.system.annotation.LogOperation;
import com.gzxn.core.system.api.IAreaCommonApi;
import com.gzxn.core.system.api.ISystemCommonApi;
import com.gzxn.core.system.dto.AreaDto;
import com.gzxn.core.system.dto.LoginUser;
import com.gzxn.core.system.dto.SmsDto;
import com.gzxn.core.system.dto.SystemSetting;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.system.dto.FindPwdDto;
import com.gzxn.ebp.system.dto.LoginDto;
import com.gzxn.ebp.system.entity.Loginrecord;
import com.gzxn.ebp.system.entity.User;
import com.gzxn.ebp.system.service.ILoginrecordService;
import com.gzxn.ebp.system.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Package: com.gzxn.ebp.system.controller
 * @ClassName: IndexController
 * @Author: CodeBird
 * @Date: 2022-03-06 19:21
 * @Description: 主页-控制器
 */
@Api(tags = "index")
@RestController
@RequestMapping("/")
public class IndexController extends BaseController {
    @Autowired
    private ISystemCommonApi iSystemCommonApi;
    @Autowired
    private IAreaCommonApi iAreaCommonApi;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ILoginrecordService iLoginrecordService;
    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public ResponseBean<String> login(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        // 生成登录日志
        Loginrecord loginrecord = new Loginrecord();
        loginrecord.setUseragent(ServletUtil.getHeader(request, "User-Agent", "UTF-8"));
        Date date = new Date();
        loginrecord.setLogintime(date);
        // 登录是否成功 1-成功 2-失败
        loginrecord.setLoginresult(2);
        loginrecord.setIp(ServletUtil.getClientIP(request));
        String code = (String) redisUtil.get(CacheConstant.CACHE_VERIFYCODE_PREFIX + CacheConstant.CACHE_SEPARATOR + loginDto.getKey());
        if (StrUtil.equals(loginDto.getCode(), code)) {
            QueryWrapper<User> queryWrapper = new QueryWrapper();
            queryWrapper.eq("lname", loginDto.getName()).or().eq("mobile", loginDto.getName()).or().eq("email", loginDto.getName());
            User user = iUserService.getOne(queryWrapper);
            if (ObjectUtil.isNotEmpty(user)) {
                loginrecord.setUid(user.getId());
                loginrecord.setCrer(user.getId());
                loginrecord.setMder(user.getId());
                if (ObjectUtil.equal(user.getIsdisable(), 2)) {
                    if (StrUtil.equals(user.getPwd(), SecureUtil.md5(loginDto.getPwd()))) {
                        // 生成token
                        String token = iSystemCommonApi.generateToken(user.getId());
                        // 存放token信息到redis
                        iSystemCommonApi.getCacheLoginUser(token);
//						iSystemCommonApi.getCacheLoginUserAndRemoveOther(token);
                        // 登录是否成功 1-成功 2-失败
                        loginrecord.setLoginresult(1);
                        iLoginrecordService.save(loginrecord);
                        return ResponseBean.Success("登录成功。", token);
                    } else {
                        iLoginrecordService.save(loginrecord);
                        return ResponseBean.Fail("密码错误。");
                    }
                } else {
                    iLoginrecordService.save(loginrecord);
                    return ResponseBean.Fail("帐号禁用，请联系管理员。");
                }
            } else {
                return ResponseBean.Fail("帐号不存在。");
            }
        } else {
            return ResponseBean.Fail("验证码错误。");
        }
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @LogOperation("获取用户信息")
    @ApiOperation(value = "获取用户信息")
    @GetMapping("/getInfo")
    public ResponseBean<LoginUser> getInfo(HttpServletRequest request) {
        return ResponseBean.Success(iSystemCommonApi.refreshTokenCacheLoginUser(this.getToken(request)));
    }

    /**
     * 获取未读消息
     *
     * @return
     */
    @LogOperation("获取未读消息")
    @ApiOperation(value = "获取未读消息")
    @GetMapping("/getUnReadSms")
    public ResponseBean<List<SmsDto>> getUnReadSms(HttpServletRequest request) {
        return ResponseBean.Success(iSystemCommonApi.getUnReadSms((String) request.getAttribute(Constant.REQUEST_ATTRIBUTE_UID)));
    }

    @LogOperation("登出")
    @ApiOperation(value = "登出")
    @GetMapping("/logout")
    public ResponseBean logout(HttpServletRequest request) {
        iSystemCommonApi.removeCacheLoginUserByToken(this.getToken(request));
        return ResponseBean.Success("注销成功。");
    }

    @LogOperation("发送忘记密码邮件")
    @ApiOperation(value = "发送忘记密码邮件")
    @GetMapping("/sendFindPwdEmail")
    public ResponseBean sendFindPwdEmail(String email) {
        // 是否禁用：1-是、2-否
        User user = iUserService.getOne(new QueryWrapper<User>().eq("email", email).eq("isdisable", "2"));
        if (ObjectUtil.isNotEmpty(user)) {
            String code = RandomUtil.randomString(6);
            redisUtil.set(CacheConstant.CACHE_FIND_PWD_EMAIL_PREFIX + CacheConstant.CACHE_SEPARATOR + email, code, 70);
            iSystemCommonApi.sendEmail(user.getEmail(), "【EBP】忘记密码", "尊敬的" + user.getLname() + "，您好！\r\n" + "\r\n" + "您的验证码为" + code + "，请于1分钟内正确输入验证码，如非本人操作，请忽略。", false);
            return ResponseBean.Success("邮件已发送。");
        } else {
            return ResponseBean.Fail("找不到绑定该邮件的帐号,或该帐号被禁用。");
        }

    }

    @LogOperation("找回密码")
    @ApiOperation(value = "找回密码")
    @PutMapping("/findPwd")
    public ResponseBean findPwd(@RequestBody FindPwdDto findPwdDto) {
        // 尝试获取邮件的验证码
        String redisCode = (String) redisUtil.get(CacheConstant.CACHE_FIND_PWD_EMAIL_PREFIX + CacheConstant.CACHE_SEPARATOR + findPwdDto.getEmail());
        if (StrUtil.isEmpty(redisCode)) {
            return ResponseBean.Fail("验证码超时了。");
        }
        if (!StrUtil.equals(redisCode, findPwdDto.getCode())) {
            return ResponseBean.Fail("验证码校验失败，请检查。");
        }
        // 查找绑定的用户
        // 是否禁用：1-是、2-否
        User user = iUserService.getOne(new QueryWrapper<User>().eq("email", findPwdDto.getEmail()).eq("isdisable", "2"));
        user.setPwd(SecureUtil.md5(findPwdDto.getNewPwd()));
        iUserService.updateById(user);
        return ResponseBean.Success("修改成功。");
    }

    /**
     * 获取系统配置
     *
     * @return
     */
    @LogOperation("获取系统配置")
    @ApiOperation(value = "获取系统配置")
    @GetMapping("/getSystemSetting")
    public ResponseBean<SystemSetting> getSystemSetting() {
        return ResponseBean.Success(iSystemCommonApi.getSystemSetting());
    }

    /**
     * 获取所有地区
     *
     * @return
     */
    @ApiOperation(value = "获取所有地区")
    @GetMapping("/getAllAreas")
    public ResponseBean<List<AreaDto>> getAllAreas() {
        return ResponseBean.Success(iAreaCommonApi.getAllAreas());
    }

    @LogOperation("获取验证码")
    @ApiOperation(value = "获取验证码")
    @GetMapping("/getVerifyCode")
    public ResponseBean<String> getVerifyCode(String key) {
        // 定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(90, 40, 4, 4);
        // ShearCaptcha captcha = new ShearCaptcha(200, 100, 4, 4);
        String code = captcha.getCode();
        // redis设置验证码，时效5分钟
        redisUtil.set(CacheConstant.CACHE_VERIFYCODE_PREFIX + CacheConstant.CACHE_SEPARATOR + key, code, 60 * 5);
        // 图形验证码写出，可以写出到文件，也可以写出到流
        return ResponseBean.Success(captcha.getImageBase64());
    }
}
