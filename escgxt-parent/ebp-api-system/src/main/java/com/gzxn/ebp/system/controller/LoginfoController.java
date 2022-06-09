package com.gzxn.ebp.system.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.system.annotation.LogOperation;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.system.entity.Loginfo;
import com.gzxn.ebp.system.service.ILoginfoService;
import com.gzxn.ebp.system.vo.LoginfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @Package: com.gzxn.ebp.system.controller
 * @ClassName: LoginfoController
 * @Author: CodeBird
 * @Date: 2022-03-06 19:22
 * @Description: 日志信息-控制器
 */
@Api(tags = "操作日志")
@RestController
@RequestMapping("/system/loginfo")
public class LoginfoController extends BaseController {

    @Autowired
    private ILoginfoService iLoginfoService;

    @LogOperation("列表")
    @RequiresPermissions(value = { "00000040" })
    @ApiOperation(value = "列表")
    @GetMapping("/list")
    public ResponseBean<Page<Loginfo>> list(@ApiParam(value = "用户名") @RequestParam(name = "uname", required = false) String uname, @ApiParam(value = "模块") @RequestParam(name = "module", required = false) String module, @ApiParam(value = "操作") @RequestParam(name = "operation", required = false) String operation, @ApiParam(value = "日志类型") @RequestParam(name = "type", required = false) String type, @ApiParam(value = "开始时间") @RequestParam(name = "beginTime", required = false) String beginTime, @ApiParam(value = "结束时间") @RequestParam(name = "endTime", required = false) String endTime, Page page) {
        QueryWrapper<LoginfoVo> queryWrapper = new QueryWrapper<LoginfoVo>();
        if (!StrUtil.isBlank(uname)) {
            queryWrapper.like("u.name", uname);
        }
        if (!StrUtil.isBlank(module)) {
            queryWrapper.like("l.module", module);
        }
        if (!StrUtil.isBlank(operation)) {
            queryWrapper.like("l.operation", operation);
        }
        if (!StrUtil.isBlank(type)) {
            queryWrapper.eq("l.type", type);
        } else {
            // 默认系统日志
            queryWrapper.eq("l.type", 1);
        }
        if (!StrUtil.isBlank(beginTime)) {
            queryWrapper.gt("l.optime", beginTime);
        }
        if (!StrUtil.isBlank(endTime)) {
            queryWrapper.lt("l.optime", endTime);
        }
        queryWrapper.orderByDesc("l.optime");

        return ResponseBean.Success(iLoginfoService.pageVo(page, queryWrapper));
    }

    @RequiresPermissions(value = { "000000400005" })
    @ApiOperation(value = "删除")
    @DeleteMapping("/remove")
    public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
        if (iLoginfoService.removeByIds(Arrays.asList(ids))) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @RequiresPermissions(value = { "000000400000" })
    @ApiOperation(value = "详细")
    @GetMapping("/toDetail")
    public ResponseBean<LoginfoVo> toDetail(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
        LoginfoVo loginfo = iLoginfoService.getVoById(id);
        if (ObjectUtil.isNotNull(loginfo)) {
            return ResponseBean.Success(loginfo);
        } else {
            return ResponseBean.Fail();
        }
    }

}
