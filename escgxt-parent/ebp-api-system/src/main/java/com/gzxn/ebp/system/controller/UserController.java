package com.gzxn.ebp.system.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.constant.Constant;
import com.gzxn.core.system.annotation.LogModule;
import com.gzxn.core.system.annotation.LogOperation;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.system.dto.UserDto;
import com.gzxn.ebp.system.entity.User;
import com.gzxn.ebp.system.entity.UserRole;
import com.gzxn.ebp.system.service.IUserRoleService;
import com.gzxn.ebp.system.service.IUserService;
import com.gzxn.ebp.system.vo.UserToUpdateVo;
import com.gzxn.ebp.system.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @Package: com.gzxn.ebp.system.controller
 * @ClassName: UserController
 * @Author: CodeBird
 * @Date: 2022-03-06 19:26
 * @Description: 用户表-控制器
 */
@Api(tags = "用户表")
@LogModule(value = "用户管理")
@RestController
@RequestMapping("/system/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IUserRoleService iUserRoleService;

    @LogOperation("列表")
    @ApiOperation(value = "列表")
    @RequiresPermissions(value = { "00000000" })
    @GetMapping("/list")
    public ResponseBean<Page<UserVo>> list(@ApiParam(value = "名字") @RequestParam(name = "name", required = false) String name, @ApiParam(value = "登录名") @RequestParam(name = "lname", required = false) String lname, @ApiParam(value = "手机") @RequestParam(name = "mobile", required = false) String mobile, @ApiParam(value = "邮件") @RequestParam(name = "email", required = false) String email, @ApiParam(value = "部门id") @RequestParam(name = "did", required = false) String did, @ApiParam(value = "角色id") @RequestParam(name = "rid", required = false) String rid, Page page) {
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
        if (StrUtil.isNotBlank(email)) {
            queryWrapper.like("email", email);
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

    @LogOperation("添加")
    @ApiOperation(value = "添加")
    @RequiresPermissions(value = { "000000000005" })
    @PostMapping("/save")
    public ResponseBean save(@RequestBody UserDto recode) {
        recode.setPwd(SecureUtil.md5(Constant.USER_DEFUALT_PWD));
        recode.setIsdisable(2);
        if (iUserService.saveDto(recode)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @LogOperation("去编辑")
    @ApiOperation(value = "去编辑")
    @RequiresPermissions(value = { "000000000010" })
    @GetMapping("/toUpdate")
    public ResponseBean<UserToUpdateVo> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
        User user = iUserService.getById(id);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", user.getId());
        UserRole userRole = iUserRoleService.getOne(queryWrapper);

        UserToUpdateVo userToUpdateVo = new UserToUpdateVo();
        userToUpdateVo.setUser(user);
        userToUpdateVo.setUserRole(userRole);
        if (ObjectUtil.isNotNull(user)) {
            return ResponseBean.Success(userToUpdateVo);
        } else {
            return ResponseBean.Fail();
        }
    }

    @LogOperation("编辑")
    @ApiOperation(value = "编辑")
    @RequiresPermissions(value = { "000000000010" })
    @PutMapping("/update")
    public ResponseBean update(@RequestBody UserDto recode) {
        if (iUserService.updateDtoById(recode)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @LogOperation("删除")
    @ApiOperation(value = "删除")
    @RequiresPermissions(value = { "000000000015" })
    @DeleteMapping("/remove")
    public ResponseBean delete(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
        if (iUserService.removeByIds(Arrays.asList(ids))) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }
}
