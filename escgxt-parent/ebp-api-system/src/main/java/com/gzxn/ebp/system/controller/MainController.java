package com.gzxn.ebp.system.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.system.annotation.LogModule;
import com.gzxn.core.system.annotation.LogOperation;
import com.gzxn.core.system.api.ISystemCommonApi;
import com.gzxn.core.system.dto.LoginUser;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.system.dto.UserDto;
import com.gzxn.ebp.system.entity.Loginrecord;
import com.gzxn.ebp.system.entity.User;
import com.gzxn.ebp.system.service.ILoginrecordService;
import com.gzxn.ebp.system.service.IUserService;
import com.gzxn.ebp.system.vo.LoginrecordVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Package: com.gzxn.ebp.system.controller
 * @ClassName: MainController
 * @Author: CodeBird
 * @Date: 2022-03-06 19:23
 * @Description: 基础管理-控制器
 */
@LogModule(value = "基础管理")
@Api(tags = "基础管理")
@RestController
@RequestMapping("/main")
public class MainController extends BaseController {
    @Autowired
    private ISystemCommonApi systemCommonApi;
    @Autowired
    private ILoginrecordService iLoginrecordService;
    @Autowired
    private IUserService iUserService;

    @LogOperation("获取用户信息")
    @ApiOperation(value = "获取用户信息")
    @GetMapping(value = "/getInfo")
    public ResponseBean<LoginUser> getInfo(Page page, HttpServletRequest request) {
        // 获取用户信息
        LoginUser loginUser = systemCommonApi.getLoginUser(getToken(request));
        return ResponseBean.Success(loginUser);
    }

    @LogOperation("登录记录年统计")
    @ApiOperation(value = "登录记录年统计")
    @GetMapping(value = "/getLoginrecordCountYear")
    public ResponseBean<List<Integer>> getLoginrecordCountYear(Page page, HttpServletRequest request) {
        LoginUser loginUser = systemCommonApi.getLoginUser(getToken(request));
        return ResponseBean.Success(iLoginrecordService.countYear(DateUtil.thisYear(), loginUser.getId()));
    }

    @LogOperation("获取登录记录")
    @ApiOperation(value = "获取登录记录")
    @GetMapping(value = "/getLoginrecord")
    public ResponseBean<Page<Loginrecord>> getLoginrecord(Page page, HttpServletRequest request) {
        LoginUser loginUser = systemCommonApi.getLoginUser(getToken(request));
        QueryWrapper<LoginrecordVo> queryWrapper = new QueryWrapper<LoginrecordVo>();
        queryWrapper.orderByDesc("l.logintime");
        queryWrapper.eq("u.id", loginUser.getId());
        return ResponseBean.Success(iLoginrecordService.pageVo(page, queryWrapper));
    }

    /**
     * 去编辑用户信息
     *
     * @param request
     * @return
     */
    @LogOperation("去编辑用户信息")
    @ApiOperation(value = "去编辑用户信息")
    @GetMapping(value = "/toUpdateInfo")
    public ResponseBean<LoginUser> toUpdateInfo(HttpServletRequest request) {
        return ResponseBean.Success(systemCommonApi.getLoginUser(getToken(request)));
    }

    /**
     * 编辑用户信息
     *
     * @param user
     * @return
     */
    @LogOperation("编辑用户信息")
    @ApiOperation(value = "编辑用户信息")
    @PutMapping(value = "/updateInfo")
    public ResponseBean updateInfo(@RequestBody User user, HttpServletRequest request) {
        if (iUserService.updateById(user)) {
            systemCommonApi.refreshTokenCacheLoginUser(getToken(request));
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    /**
     * 编辑密码
     *
     * @param user
     * @return
     */
    @LogOperation("编辑密码")
    @ApiOperation(value = "编辑密码")
    @PutMapping(value = "/updatePwd")
    public ResponseBean updatePwd(@RequestBody UserDto user, HttpServletRequest request) {
        LoginUser loginUser = systemCommonApi.getLoginUser(getToken(request));
        User old = iUserService.getById(loginUser.getId());
        // 验证旧密码
        // 获取数据库的密码
        if (!StrUtil.equals(old.getPwd(), SecureUtil.md5(user.getPwd()))) {
            return ResponseBean.Fail("旧密码错误");
        } else {
            // 旧密码正确
            // 编辑密码
            User user2 = new User();
            user2.setId(old.getId());
            user2.setPwd(SecureUtil.md5(user.getNewPwd()));
            if (iUserService.updateById(user2)) {
                return ResponseBean.Success();
            } else {
                return ResponseBean.Fail();
            }
        }
    }

}
