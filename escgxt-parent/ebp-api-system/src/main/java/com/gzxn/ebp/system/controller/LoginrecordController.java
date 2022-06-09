package com.gzxn.ebp.system.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.system.annotation.LogModule;
import com.gzxn.core.system.annotation.LogOperation;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.system.entity.Loginrecord;
import com.gzxn.ebp.system.service.ILoginrecordService;
import com.gzxn.ebp.system.vo.LoginrecordVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @Package: com.gzxn.ebp.system.controller
 * @ClassName: LoginrecordController
 * @Author: CodeBird
 * @Date: 2022-03-06 19:23
 * @Description: 登录记录-控制器
 */
@Api(tags = "登录日志")
@LogModule(value = "登录日志")
@RestController
@RequestMapping("/system/loginrecord")
public class LoginrecordController extends BaseController {

    @Autowired
    private ILoginrecordService iLoginrecordService;

    @LogOperation("列表")
    @RequiresPermissions(value = { "00000035" })
    @ApiOperation(value = "列表")
    @GetMapping("/list")
    public ResponseBean<Page<Loginrecord>> list(Page page, @ApiParam(value = "用户名") @RequestParam(name = "uname", required = false) String uname, @ApiParam(value = "手机号") @RequestParam(name = "mobile", required = false) String mobile, @ApiParam(value = "登录记录") @RequestParam(name = "loginresult", required = false) String loginresult) {
        QueryWrapper<LoginrecordVo> queryWrapper = new QueryWrapper<LoginrecordVo>();

        if (!StrUtil.isBlank(uname)) {
            queryWrapper.like("u.name", uname);
        }
        if (!StrUtil.isBlank(mobile)) {
            queryWrapper.like("u.mobile", mobile);
        }
        if (!StrUtil.isBlank(loginresult)) {
            queryWrapper.like("l.loginresult", loginresult);
        }
        queryWrapper.orderByDesc("l.logintime");
        return ResponseBean.Success(iLoginrecordService.pageVo(page, queryWrapper));
    }

    @LogOperation("删除")
    @RequiresPermissions(value = { "000000350005" })
    @ApiOperation(value = "删除")
    @DeleteMapping("/remove")
    public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
        if (iLoginrecordService.removeByIds(Arrays.asList(ids))) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @LogOperation("详细")
    @RequiresPermissions(value = { "000000350000" })
    @ApiOperation(value = "详细")
    @GetMapping("/toDetail")
    public ResponseBean<LoginrecordVo> toDetail(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
        LoginrecordVo loginrecordVo = iLoginrecordService.getVoById(id);
        if (ObjectUtil.isNotNull(loginrecordVo)) {
            return ResponseBean.Success(loginrecordVo);
        } else {
            return ResponseBean.Fail();
        }
    }
}
