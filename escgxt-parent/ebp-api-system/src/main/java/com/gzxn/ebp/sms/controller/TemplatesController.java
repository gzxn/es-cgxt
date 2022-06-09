package com.gzxn.ebp.sms.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.sms.entity.Templates;
import com.gzxn.ebp.sms.service.ITemplatesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @Package: com.gzxn.ebp.sms.controller
 * @ClassName: TemplatesController
 * @Author: CodeBird
 * @Date: 2022-03-06 19:19
 * @Description: 信息模版-控制器
 */
@Api(tags = "信息模版")
@RestController
@RequestMapping("/sms/templates")
public class TemplatesController extends BaseController {

    @Autowired
    private ITemplatesService iTemplatesService;

    @ApiOperation(value = "列表")
    @RequiresPermissions("002000150000")
    @GetMapping("/list")
    public ResponseBean<Page<Templates>> list(
            @ApiParam(value = "模版标识") @RequestParam(name = "code", required = false) String code,
            @ApiParam(value = "模版名称") @RequestParam(name = "name", required = false) String name,Page page) {
        QueryWrapper<Templates> queryWrapper = new QueryWrapper<Templates>();
        if(StrUtil.isNotBlank(code)) {
            queryWrapper.like("code", code);
        }
        if(StrUtil.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }
        queryWrapper.orderByDesc("crtm");
        return ResponseBean.Success(iTemplatesService.page(page, queryWrapper));
    }

    @ApiOperation(value = "添加")
    @RequiresPermissions("002000150000")
    @PostMapping("/save")
    public ResponseBean save(@RequestBody Templates record) {
        // 模版标识查重
        Templates tmp = iTemplatesService.getOne(new QueryWrapper<Templates>().eq("code", record.getCode()));
        if (ObjectUtil.isNotEmpty(tmp)) {
            return ResponseBean.Fail("模版标识已存在。");
        }
        if (iTemplatesService.save(record)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "去编辑")
    @RequiresPermissions(value = { "002000150005", "002000150015" }, logical = Logical.OR)
    @GetMapping("/toUpdate")
    public ResponseBean<Templates> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
        Templates templates = iTemplatesService.getById(id);
        if (ObjectUtil.isNotNull(templates)) {
            return ResponseBean.Success(templates);
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "编辑")
    @RequiresPermissions("002000150005")
    @PutMapping("/update")
    public ResponseBean update(@RequestBody Templates record) {
        if (iTemplatesService.updateById(record)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "删除")
    @RequiresPermissions("002000150010")
    @DeleteMapping("/remove")
    public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
        if (iTemplatesService.removeByIds(Arrays.asList(ids))) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }
}