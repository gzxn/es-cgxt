package com.gzxn.ebp.system.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.system.annotation.LogModule;
import com.gzxn.core.system.annotation.LogOperation;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.system.entity.Department;
import com.gzxn.ebp.system.service.IDepartmentService;
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
 * @ClassName: DepartmentController
 * @Author: CodeBird
 * @Date: 2022-03-06 19:20
 * @Description: 部门表-控制器
 */
@Api(tags = "部门表")
@LogModule(value = "部门管理")
@RestController
@RequestMapping("/system/department")
public class DepartmentController extends BaseController {

    @Autowired
    private IDepartmentService iDepartmentService;

    @LogOperation("列表")
    @ApiOperation(value = "列表")
    @RequiresPermissions(value = { "00000005" })
    @GetMapping("/list")
    public ResponseBean<List<Department>> list() {
        return ResponseBean.Success(iDepartmentService.list(new QueryWrapper<Department>().orderByAsc("level","sort")));
    }

    @LogOperation("添加")
    @ApiOperation(value = "添加")
    @RequiresPermissions(value = { "000000050005" })
    @PostMapping("/save")
    public ResponseBean save(@RequestBody Department recode) {
        // 防止部门编码重复
        Department department = iDepartmentService.getOne(new QueryWrapper<Department>().eq("code", recode.getCode()));
        if (ObjectUtil.isNotEmpty(department)) {
            return ResponseBean.Fail("部门标识	重复");
        }
        if (iDepartmentService.save(recode)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @LogOperation("去编辑")
    @ApiOperation(value = "去编辑")
    @RequiresPermissions(value = { "000000050010" })
    @GetMapping("/toUpdate")
    public ResponseBean<Department> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
        Department department = iDepartmentService.getById(id);
        if (ObjectUtil.isNotEmpty(department)) {
            return ResponseBean.Success(department);
        } else {
            return ResponseBean.Fail();
        }
    }

    @LogOperation("编辑")
    @ApiOperation(value = "编辑")
    @RequiresPermissions(value = { "000000050010" })
    @PutMapping("/update")
    public ResponseBean update(@RequestBody Department recode) {
        if (iDepartmentService.updateById(recode)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @LogOperation("删除")
    @ApiOperation(value = "删除")
    @RequiresPermissions(value = { "000000050015" })
    @DeleteMapping("/remove")
    public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
        if (iDepartmentService.removeByIds(Arrays.asList(ids))) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }
}
