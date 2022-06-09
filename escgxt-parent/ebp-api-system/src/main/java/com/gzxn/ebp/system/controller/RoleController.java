package com.gzxn.ebp.system.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.system.annotation.LogModule;
import com.gzxn.core.system.annotation.LogOperation;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.system.dto.RoleDto;
import com.gzxn.ebp.system.entity.Role;
import com.gzxn.ebp.system.entity.RoleDepartment;
import com.gzxn.ebp.system.entity.RolePermission;
import com.gzxn.ebp.system.service.IRoleDepartmentService;
import com.gzxn.ebp.system.service.IRolePermissionService;
import com.gzxn.ebp.system.service.IRoleService;
import com.gzxn.ebp.system.vo.RoleToUpdateVo;
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
 * @ClassName: RoleController
 * @Author: CodeBird
 * @Date: 2022-03-06 19:25
 * @Description: 角色表-控制器
 */
@Api(tags = "角色表")
@LogModule(value = "角色管理")
@RestController
@RequestMapping("/system/role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private IRolePermissionService iRolePermissionService;
    @Autowired
    private IRoleDepartmentService iRoleDepartmentService;

    @LogOperation("列表")
    @RequiresPermissions(value = { "00000010" })
    @ApiOperation(value = "列表")
    @GetMapping("/list")
    public ResponseBean<List<Role>> list() {
        return ResponseBean.Success(iRoleService.list(new QueryWrapper<Role>().orderByAsc("level", "sort")));
    }

    @LogOperation("添加")
    @RequiresPermissions(value = { "000000100005" })
    @ApiOperation(value = "添加")
    @PostMapping("/save")
    public ResponseBean save(@RequestBody RoleDto recode) {
        // 防止角色编码重复
        Role role = iRoleService.getOne(new QueryWrapper<Role>().eq("code", recode.getCode()));
        if (ObjectUtil.isNotEmpty(role)) {
            return ResponseBean.Fail("角色标识	重复");
        }
        if (iRoleService.saveDto(recode)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @LogOperation("去编辑")
    @RequiresPermissions(value = { "000000100010" })
    @ApiOperation(value = "去编辑")
    @GetMapping("/toUpdate")
    public ResponseBean<RoleToUpdateVo> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
        Role role = iRoleService.getById(id);
        // 查询角色授权
        QueryWrapper queryWrapperrp = new QueryWrapper();
        queryWrapperrp.eq("rid", role.getId());
        List<RolePermission> rolePermissions = iRolePermissionService.list(queryWrapperrp);

        // 查询角色部门
        QueryWrapper queryWrapperrd = new QueryWrapper();
        queryWrapperrd.eq("rid", role.getId());
        RoleDepartment roleDepartment = iRoleDepartmentService.getOne(queryWrapperrd);

        RoleToUpdateVo roleToUpdateVo = new RoleToUpdateVo();
        roleToUpdateVo.setRole(role);
        roleToUpdateVo.setRolePermissions(rolePermissions);
        roleToUpdateVo.setRoleDepartment(roleDepartment);
        if (ObjectUtil.isNotEmpty(role)) {
            return ResponseBean.Success(roleToUpdateVo);
        } else {
            return ResponseBean.Fail();
        }
    }

    @LogOperation("编辑")
    @RequiresPermissions(value = { "000000100010" })
    @ApiOperation(value = "编辑")
    @PutMapping("/update")
    public ResponseBean update(@RequestBody RoleDto recode) {
        if (iRoleService.updateDtoById(recode)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @LogOperation("删除")
    @RequiresPermissions(value = { "000000100015" })
    @ApiOperation(value = "删除")
    @DeleteMapping("/remove")
    public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
        if (iRoleService.removeByIds(Arrays.asList(ids))) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }
}
