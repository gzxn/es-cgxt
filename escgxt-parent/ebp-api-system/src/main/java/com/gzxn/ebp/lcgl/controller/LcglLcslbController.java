package com.gzxn.ebp.lcgl.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.lcgl.entity.LcglLcslb;
import com.gzxn.ebp.lcgl.service.ILcglLcslbService;
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
 * @ClassName: LcglLcslbController
 * @Description: 流程实例表-控制器
 * @author: CodeBird
 * @date:  2022-03-26 14:03:56 
 */
@Api(tags = "流程实例表")
@RestController
@RequestMapping("/lcgl/lcglLcslb")
public class LcglLcslbController extends BaseController {
	
	@Autowired
	private ILcglLcslbService iLcglLcslbService;

	@ApiOperation(value = "列表")
	@RequiresPermissions("")
	@GetMapping("/list")
	public ResponseBean<Page<LcglLcslb>> list(Page page) {
		QueryWrapper<LcglLcslb> queryWrapper = new QueryWrapper<LcglLcslb>();
		return ResponseBean.Success(iLcglLcslbService.page(page,queryWrapper));
	}

	@ApiOperation(value = "添加")
	@RequiresPermissions("")
	@PostMapping("/save")
	public ResponseBean save(@RequestBody LcglLcslb record) {
		if (iLcglLcslbService.save(record)) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}

	@ApiOperation(value = "去编辑")
	@RequiresPermissions("")
	@GetMapping("/toUpdate")
	public ResponseBean<LcglLcslb> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
		LcglLcslb lcglLcslb = iLcglLcslbService.getById(id);
		if (ObjectUtil.isNotNull(lcglLcslb)) {
			return ResponseBean.Success(lcglLcslb);
		} else {
			return ResponseBean.Fail();
		}
	}


	@ApiOperation(value = "编辑")
	@RequiresPermissions("")
	@PutMapping("/update")
	public ResponseBean update(@RequestBody LcglLcslb record) {
		if (iLcglLcslbService.updateById(record)) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}

	@ApiOperation(value = "删除")
	@RequiresPermissions("")
	@DeleteMapping("/remove")
	public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
		if (iLcglLcslbService.removeByIds(Arrays.asList(ids))) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}
}
