package com.gzxn.ebp.components.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.system.entity.Role;
import com.gzxn.ebp.system.service.IRoleService;
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
 * @ClassName: RoleComponentContrller
 * @Author: CodeBird
 * @Date: 2022-03-06 19:33
 * @Description: 角色组件-控制器
 */
@Api(tags = "角色组件-控制器")
@RestController
@RequestMapping("/components/roleComponent")
public class RoleComponentContrller extends BaseController {
    @Autowired
    private IRoleService iRoleService;

    @ApiOperation(value = "列表")
    @GetMapping("/list")
    public ResponseBean<List<Role>> list() {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>();
        queryWrapper.orderByAsc("level");
        queryWrapper.orderByAsc("sort");
        queryWrapper.orderByAsc("crtm");
        // 查询所有的角色
        return ResponseBean.Success(iRoleService.list(queryWrapper));
    }

    @ApiOperation(value = "根据ids查询")
    @GetMapping("/getByIds")
    public ResponseBean<List<Role>> getByIds(@ApiParam(value = "ids") @RequestParam(name = "ids", required = false) String[] ids) {
        List<Role> roles = null;
        if (ObjectUtil.isNotEmpty(ids)) {
            roles = new ArrayList<Role>();
            for (String id : ids) {
                roles.add(iRoleService.getById(id));
            }
        }
        return ResponseBean.Success(roles);
    }

}
