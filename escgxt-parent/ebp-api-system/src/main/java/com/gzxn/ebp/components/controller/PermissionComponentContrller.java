package com.gzxn.ebp.components.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.system.entity.Permission;
import com.gzxn.ebp.system.service.IPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.gzxn.ebp.components.controller
 * @ClassName: PermissionComponentContrller
 * @Author: CodeBird
 * @Date: 2022-03-06 19:32
 * @Description: 权限组件-控制器
 */
@Api(tags = "角色组件-控制器")
@RestController
@RequestMapping("/components/permissionComponent")
public class PermissionComponentContrller extends BaseController {
    @Autowired
    private IPermissionService iPermissionService;

    @ApiOperation(value = "列表")
    @GetMapping("/list")
    public ResponseBean<List<Permission>> list() {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<Permission>();
        queryWrapper.orderByAsc("level");
        queryWrapper.orderByAsc("sort");
        queryWrapper.orderByAsc("crtm");
        List<Permission> list = iPermissionService.list(queryWrapper);
        return ResponseBean.Success(list);
    }

    @ApiOperation(value = "根据ids查询")
    @GetMapping("/getByIds")
    public ResponseBean<List<Permission>> getByIds(
            @ApiParam(value = "ids") @RequestParam(name = "ids", required = false) String[] ids) {
        List<Permission> permissions = null;
        if (ObjectUtil.isNotEmpty(ids)) {
            permissions = new ArrayList<Permission>();
            for (String id : ids) {
                permissions.add(iPermissionService.getById(id));
            }
        }
        return ResponseBean.Success(permissions);
    }

}
