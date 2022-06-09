package com.gzxn.ebp.lcgl.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.lcgl.entity.LcglLccljs;
import com.gzxn.ebp.lcgl.service.ILcglLccljsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * Copyright  2022-03-26 QLH. Tech Ltd. All rights reserved.
 * 
 * @Package: com.gzxn.ebp.lcgl.controller
 * @ClassName: LcglLccljsController
 * @Description: 流程处理角色表-控制器
 * @author: CodeBird
 * @date:  2022-03-26 14:03:56 
 */
@Api(tags = "流程处理角色表")
@RestController
@RequestMapping("/lcgl/lcglLccljs")
public class LcglLccljsController extends BaseController {
	
	@Autowired
	private ILcglLccljsService iLcglLccljsService;

	@ApiOperation(value = "列表")
	@RequiresPermissions("")
	@GetMapping("/list")
	public ResponseBean<Page<LcglLccljs>> list(Page page) {
		QueryWrapper<LcglLccljs> queryWrapper = new QueryWrapper<LcglLccljs>();
		return ResponseBean.Success(iLcglLccljsService.page(page,queryWrapper));
	}

	@ApiOperation(value = "添加")
	@RequiresPermissions("")
	@PostMapping("/save")
	public ResponseBean save(@RequestBody LcglLccljs record) {
		if (iLcglLccljsService.save(record)) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}

	@ApiOperation(value = "去编辑")
	@RequiresPermissions("")
	@GetMapping("/toUpdate")
	public ResponseBean<LcglLccljs> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
		LcglLccljs lcglLccljs = iLcglLccljsService.getById(id);
		if (ObjectUtil.isNotNull(lcglLccljs)) {
			return ResponseBean.Success(lcglLccljs);
		} else {
			return ResponseBean.Fail();
		}
	}


	@ApiOperation(value = "编辑")
	@RequiresPermissions("")
	@PutMapping("/update")
	public ResponseBean update(@RequestBody LcglLccljs record) {
		if (iLcglLccljsService.updateById(record)) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}

	@ApiOperation(value = "删除")
	@RequiresPermissions("")
	@DeleteMapping("/remove")
	public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
		if (iLcglLccljsService.removeByIds(Arrays.asList(ids))) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}
}
