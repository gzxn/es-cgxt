package com.gzxn.ebp.system.controller;

import cn.hutool.core.util.ObjectUtil;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.system.entity.Area;
import com.gzxn.ebp.system.service.IAreaService;
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
 * @ClassName: AreaController
 * @Author: CodeBird
 * @Date: 2022-03-06 18:09
 * @Description: 地区表-控制器
 */
@Api(tags = "地区表")
@RestController
@RequestMapping("/system/area")
public class AreaController extends BaseController {

    @Autowired
    private IAreaService iAreaService;

    @ApiOperation(value = "列表")
    @RequiresPermissions("00000045")
    @GetMapping("/list")
    public ResponseBean<List<Area>> list() {
//		QueryWrapper<Area> queryWrapper = new QueryWrapper<Area>().orderByAsc("id", "level");
        return ResponseBean.Success(iAreaService.list());
    }

    @ApiOperation(value = "添加")
    @RequiresPermissions("000000450000")
    @PostMapping("/save")
    public ResponseBean save(@RequestBody Area recode) {
        if (iAreaService.save(recode)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "去编辑")
    @RequiresPermissions("000000450005")
    @GetMapping("/toUpdate")
    public ResponseBean<Area> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
        Area area = iAreaService.getById(id);
        if (ObjectUtil.isNotNull(area)) {
            return ResponseBean.Success(area);
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "编辑")
    @RequiresPermissions("000000450005")
    @PutMapping("/update")
    public ResponseBean update(@RequestBody Area recode) {
        if (iAreaService.updateById(recode)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "删除")
    @RequiresPermissions("000000450010")
    @DeleteMapping("/remove")
    public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
        if (iAreaService.removeByIds(Arrays.asList(ids))) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }
}
