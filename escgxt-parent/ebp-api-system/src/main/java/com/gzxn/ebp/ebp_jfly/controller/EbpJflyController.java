package com.gzxn.ebp.ebp_jfly.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.system.annotation.LogModule;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.ebp_jfly.entity.EbpJfly;
import com.gzxn.ebp.ebp_jfly.service.IEbpJflyService;
import com.gzxn.ebp.system.entity.Department;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Package: com.gzxn.ebp.ebp_jfly.controller
 * @ClassName: EbpJflyController
 * @Description: 经费来源表-控制器
 * @author: CodeBird
 * @date: 2022-03-26 14:03:37
 */
@Api(tags = "经费来源表")
@LogModule(value = "经费管理")
@RestController
@RequestMapping("/ebp_jfly/ebpJfly")
public class EbpJflyController extends BaseController {

    @Autowired
    private IEbpJflyService iEbpJflyService;

    @ApiOperation(value = "列表")
//    @RequiresPermissions("5000010001")
    @GetMapping("/list")
    public ResponseBean<Page<EbpJfly>> list(@ApiParam(value = "预算项目名称") @RequestParam(name = "ysmc", required = false) String ysmc,
                                            @ApiParam(value = "预算项目编码") @RequestParam(name = "ysbm", required = false) String ysbm,
                                            @ApiParam(value = "年份") @RequestParam(name = "year", required = false) String year,
                                            @ApiParam(value = "部门id") @RequestParam(name = "did", required = false) String did,
                                            Page page) {
        QueryWrapper<EbpJfly> queryWrapper = new QueryWrapper<EbpJfly>();
        if (StrUtil.isNotBlank(ysmc)) {
            queryWrapper.like("ysmc", ysmc);
        }
        if (StrUtil.isNotBlank(ysbm)) {
            queryWrapper.like("ysbm", ysbm);
        }
        if (StrUtil.isNotBlank(year)) {
            queryWrapper.like("year", year);
        }
        if (StrUtil.isNotBlank(did)) {
            queryWrapper.like("d.id", did);
        }
        queryWrapper.orderByAsc("crtm");
        return ResponseBean.Success(iEbpJflyService.pageVo(page, queryWrapper));
    }

    @ApiOperation(value = "添加")
//    @RequiresPermissions("5000010005")
    @PostMapping("/save")
    public ResponseBean save(@RequestBody EbpJfly record) {
        if (iEbpJflyService.save(record)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "去编辑")
//    @RequiresPermissions("5000010010")
    @GetMapping("/toUpdate")
    public ResponseBean<EbpJfly> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
        EbpJfly ebpJfly = iEbpJflyService.getById(id);
        if (ObjectUtil.isNotNull(ebpJfly)) {
            return ResponseBean.Success(ebpJfly);
        } else {
            return ResponseBean.Fail();
        }
    }


    @ApiOperation(value = "编辑")
//    @RequiresPermissions("5000010010")
    @PutMapping("/update")
    public ResponseBean update(@RequestBody EbpJfly record) {
        /*UpdateWrapper<EbpJfly> jflyUpdateWrapper = new UpdateWrapper<EbpJfly>();
        jflyUpdateWrapper.set("ysmc",record.getYsmc());
        jflyUpdateWrapper.set("ysbm",record.getYsbm());
        jflyUpdateWrapper.set("year",record.getYear());
        jflyUpdateWrapper.set("jbbm",record.getJbbm());
        jflyUpdateWrapper.set("jbbmbm",record.getJbbmbm());
        jflyUpdateWrapper.set("jf_type",record.getJfType());
        jflyUpdateWrapper.eq("id",record.getId());*/
        if (iEbpJflyService.updateById(record)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "删除")
    @RequiresPermissions("5000010015")
    @DeleteMapping("/remove")
    public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
        if (iEbpJflyService.removeByIds(Arrays.asList(ids))) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "根据id获取部门列表数据")
    @GetMapping("/getDepartmentListById")
    public ResponseBean<List<Department>> getDepartmentListById(@ApiParam(value = "部门id") @RequestParam(name = "id", required = true) String id) {
        return ResponseBean.Success(iEbpJflyService.departmentVo(id));
    }

    @ApiOperation(value = "根据ids查询")
    @GetMapping("/getByIds")
    public ResponseBean<List<EbpJfly>> getByIds(@ApiParam(value = "ids") @RequestParam(name = "ids", required = false) String[] ids) {
        List<EbpJfly> ebpJfly = null;
        if (ObjectUtil.isNotEmpty(ids)) {
            ebpJfly = new ArrayList<EbpJfly>();
            for (String id : ids) {
                ebpJfly.add(iEbpJflyService.getById(id));
            }
        }
        return ResponseBean.Success(ebpJfly);
    }

}
