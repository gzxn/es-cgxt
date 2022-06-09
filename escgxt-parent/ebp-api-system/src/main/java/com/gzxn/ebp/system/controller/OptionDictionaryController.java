package com.gzxn.ebp.system.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.system.annotation.LogModule;
import com.gzxn.core.system.annotation.LogOperation;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.system.dto.OptionDictionaryDto;
import com.gzxn.ebp.system.entity.Dictionary;
import com.gzxn.ebp.system.entity.Dictionarydata;
import com.gzxn.ebp.system.service.IDictionarydataService;
import com.gzxn.ebp.system.service.IOptionDictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @Package: com.gzxn.ebp.system.controller
 * @ClassName: OptionDictionaryController
 * @Author: CodeBird
 * @Date: 2022-03-06 19:24
 * @Description: 选项字典-控制器
 */
@Api(tags = "选项字典")
@LogModule(value = "字典选项")
@RestController
@RequestMapping("/system/optionDictionary")
public class OptionDictionaryController extends BaseController {

    @Autowired
    private IDictionarydataService dictionarydataService;
    @Autowired
    private IOptionDictionaryService optionDictionaryService;

    @LogOperation("列表")
    @RequiresPermissions(value = { "00000025" })
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
        queryWrapper.eq("type", "1");
        queryWrapper.orderByDesc("crtm");
        return ResponseBean.Success(optionDictionaryService.page(page, queryWrapper));
    }

    @LogOperation("添加")
    @RequiresPermissions(value = { "000000250000" })
    @ApiOperation(value = "添加")
    @PostMapping("/save")
    public ResponseBean save(@RequestBody Dictionary recode) throws Exception {
        // 防止数据字典编码重复
        Dictionary dictionary = optionDictionaryService.getOne(new QueryWrapper<Dictionary>().eq("dkey", recode.getDkey()));
        if (ObjectUtil.isNotEmpty(dictionary)) {
            return ResponseBean.Fail("数据字典标识	重复");
        }
        if (optionDictionaryService.save(recode)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @LogOperation("去编辑")
    @RequiresPermissions(value = { "000000250005" })
    @ApiOperation(value = "去编辑")
    @GetMapping("/toUpdate")
    public ResponseBean<Dictionary> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
        Dictionary dictionary = optionDictionaryService.getById(id);
        if (ObjectUtil.isNotNull(dictionary)) {
            return ResponseBean.Success(dictionary);
        } else {
            return ResponseBean.Fail();
        }
    }

    @LogOperation("编辑")
    @RequiresPermissions(value = { "000000250005" })
    @ApiOperation(value = "编辑")
    @PutMapping("/update")
    public ResponseBean update(@RequestBody Dictionary recode) throws Exception {
        if (optionDictionaryService.updateById(recode)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @LogOperation("删除")
    @RequiresPermissions(value = { "000000250010" })
    @ApiOperation(value = "删除")
    @DeleteMapping("/remove")
    public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
        if (optionDictionaryService.removeByIds(Arrays.asList(ids))) {
            // 并且把选项数据删除
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.in("did", Arrays.asList(ids));
            dictionarydataService.remove(queryWrapper);
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @LogOperation("去编辑选项")
    @RequiresPermissions(value = { "000000250015" })
    @ApiOperation(value = "去编辑选项")
    @GetMapping("/toUpdateOption")
    public ResponseBean updateOption(@ApiParam(value = "did") @RequestParam(name = "did") String did) {
        return ResponseBean.Success(dictionarydataService.list(new QueryWrapper<Dictionarydata>().eq("did", did).orderByAsc("sort")));
    }

    @LogOperation("编辑选项")
    @RequiresPermissions(value = { "000000250015" })
    @ApiOperation(value = "编辑选项")
    @PutMapping("/updateOption")
    public ResponseBean updateOption(@RequestBody() OptionDictionaryDto optionDictionaryDto) {
        if (dictionarydataService.updateOptionDictionaryDto(optionDictionaryDto)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }
}