package com.gzxn.ebp.system.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.system.annotation.LogModule;
import com.gzxn.core.system.annotation.LogOperation;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.system.entity.Dictionary;
import com.gzxn.ebp.system.service.IDictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @Package: com.gzxn.ebp.system.controller
 * @ClassName: DictionaryController
 * @Author: CodeBird
 * @Date: 2022-03-06 19:21
 * @Description: 数据字典-控制器
 */
@Api(tags = "数据字典")
@LogModule(value = "数据字典")
@RestController
@RequestMapping("/system/dictionary")
public class DictionaryController extends BaseController {

    @Autowired
    private IDictionaryService iDictionaryService;

    @LogOperation("列表")
    @RequiresPermissions(value = { "00000020" })
    @ApiOperation(value = "列表")
    @GetMapping("/list")
    public ResponseBean<Page<Dictionary>> list(String name, String dkey, Integer isdisable, Page page) {
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<Dictionary>();
        if (StrUtil.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }
        if (StrUtil.isNotBlank(dkey)) {
            queryWrapper.like("dkey", dkey);
        }
        if (ObjectUtil.isNotEmpty(isdisable)) {
            queryWrapper.eq("isdisable", isdisable);
        }
        // 类型：1-选项、2-属性值、3-系统属性
        queryWrapper.eq("type", "2");
        queryWrapper.orderByDesc("crtm");
        return ResponseBean.Success(iDictionaryService.page(page, queryWrapper));
    }

    @LogOperation("添加")
    @RequiresPermissions(value = { "000000200005" })
    @ApiOperation(value = "添加")
    @PostMapping("/save")
    public ResponseBean save(@RequestBody Dictionary recode) {
        // 防止数据字典编码重复
        Dictionary dictionary = iDictionaryService.getOne(new QueryWrapper<Dictionary>().eq("dkey", recode.getDkey()));
        if (ObjectUtil.isNotEmpty(dictionary)) {
            return ResponseBean.Fail("数据字典标识	重复");
        }

        // 类型：1-选项、2-属性值、3-系统属性
        recode.setType(2);
        if (iDictionaryService.save(recode)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @LogOperation("去编辑")
    @RequiresPermissions(value = { "000000200010" })
    @ApiOperation(value = "去编辑")
    @GetMapping("/toUpdate")
    public ResponseBean<Dictionary> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
        Dictionary dictionary = iDictionaryService.getById(id);
        if (ObjectUtil.isNotNull(dictionary)) {
            return ResponseBean.Success(dictionary);
        } else {
            return ResponseBean.Fail();
        }
    }

    @LogOperation("编辑")
    @RequiresPermissions(value = { "000000200010" })
    @ApiOperation(value = "编辑")
    @PutMapping("/update")
    public ResponseBean update(@RequestBody Dictionary recode) {
        if (iDictionaryService.updateById(recode)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @LogOperation("删除")
    @RequiresPermissions(value = { "000000200015" })
    @ApiOperation(value = "删除")
    @DeleteMapping("/remove")
    public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
        if (iDictionaryService.removeByIds(Arrays.asList(ids))) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }
}
