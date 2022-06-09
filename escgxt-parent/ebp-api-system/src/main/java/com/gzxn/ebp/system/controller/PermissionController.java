package com.gzxn.ebp.system.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.system.annotation.LogModule;
import com.gzxn.core.system.annotation.LogOperation;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.system.entity.Permission;
import com.gzxn.ebp.system.service.IPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Package: com.gzxn.ebp.system.controller
 * @ClassName: PermissionController
 * @Author: CodeBird
 * @Date: 2022-03-06 19:25
 * @Description: 权限表-控制器
 */
@Api(tags = "权限表")
@LogModule(value = "权限管理")
@RestController
@RequestMapping("/system/permission")
public class PermissionController extends BaseController {

    @Autowired
    private IPermissionService iPermissionService;

    @LogOperation("列表")
    @RequiresPermissions(value = { "00000015" })
    @ApiOperation(value = "列表")
    @GetMapping("/list")
    public ResponseBean<List<Permission>> list() {
        QueryWrapper queryWrapper = new QueryWrapper<Permission>().orderByAsc("level", "sort");
        return ResponseBean.Success(iPermissionService.list(queryWrapper));
    }

    @LogOperation("添加")
    @RequiresPermissions(value = { "000000150005" })
    @ApiOperation(value = "添加")
    @PostMapping("/save")
    public ResponseBean save(@RequestBody Permission recode) {
        // 防止权限编码重复
        Permission permission = iPermissionService.getOne(new QueryWrapper<Permission>().eq("code", recode.getCode()));
        if (ObjectUtil.isNotEmpty(permission)) {
            return ResponseBean.Fail("权限标识	重复");
        }

        if (iPermissionService.save(recode)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @LogOperation("去编辑")
    @RequiresPermissions(value = { "000000150010" })
    @ApiOperation(value = "去编辑")
    @GetMapping("/toUpdate")
    public ResponseBean<Permission> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
        Permission department = iPermissionService.getById(id);
        if (ObjectUtil.isNotEmpty(department)) {
            return ResponseBean.Success(department);
        } else {
            return ResponseBean.Fail();
        }
    }

    @LogOperation("编辑")
    @RequiresPermissions(value = { "000000150010" })
    @ApiOperation(value = "编辑")
    @PutMapping("/update")
    public ResponseBean update(@RequestBody Permission recode) {
        if (iPermissionService.updateById(recode)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @LogOperation("删除")
    @RequiresPermissions(value = { "000000150015" })
    @ApiOperation(value = "删除")
    @DeleteMapping("/remove")
    public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
        if (iPermissionService.removeByIds(Arrays.asList(ids))) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }
}