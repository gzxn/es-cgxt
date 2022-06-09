package com.gzxn.ebp.components.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.system.entity.User;
import com.gzxn.ebp.system.service.IUserService;
import com.gzxn.ebp.system.vo.UserVo;
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
 * @ClassName: UserComponentContrller
 * @Author: CodeBird
 * @Date: 2022-03-06 19:34
 * @Description: 用户组件-控制器
 */
@Api(tags = "用户组件-控制器")
@RestController
@RequestMapping("/components/userComponent")
public class UserComponentContrller extends BaseController {
    @Autowired
    private IUserService iUserService;

    @ApiOperation(value = "列表")
    @GetMapping("/list")
    public ResponseBean<Page<UserVo>> list(
            @ApiParam(value = "名字") @RequestParam(name = "name", required = false) String name,
            @ApiParam(value = "登录名") @RequestParam(name = "lname", required = false) String lname,
            @ApiParam(value = "手机") @RequestParam(name = "mobile", required = false) String mobile,
            @ApiParam(value = "部门id") @RequestParam(name = "did", required = false) String did,
            @ApiParam(value = "角色id") @RequestParam(name = "rid", required = false) String rid,
            Page page) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        if (StrUtil.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }
        if (StrUtil.isNotBlank(lname)) {
            queryWrapper.like("lname", lname);
        }
        if (StrUtil.isNotBlank(mobile)) {
            queryWrapper.like("mobile", mobile);
        }
        if (StrUtil.isNotBlank(did)) {
            queryWrapper.like("d.id", did);
        }
        if (StrUtil.isNotBlank(rid)) {
            queryWrapper.like("r.id", rid);
        }
        queryWrapper.orderByDesc("crtm");

        return ResponseBean.Success(iUserService.pageVo(page, queryWrapper));
    }

    @ApiOperation(value = "根据ids查询")
    @GetMapping("/getByIds")
    public ResponseBean<List<User>> getByIds(@ApiParam(value = "ids") @RequestParam(name = "ids", required = false) String[] ids) {
        List<User> users = null;
        if (ObjectUtil.isNotEmpty(ids)) {
            users = new ArrayList<User>();
            for (String id : ids) {
                users.add(iUserService.getById(id));
            }
        }
        return ResponseBean.Success(users);
    }

}
