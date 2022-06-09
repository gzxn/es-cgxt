package com.gzxn.ebp.lcgl.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.lcgl.entity.LcglJdlxb;
import com.gzxn.ebp.lcgl.service.ILcglJdlxbService;
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
 * @ClassName: LcglJdlxbController
 * @Description: 流程节点历史表-控制器
 * @author: CodeBird
 * @date:  2022-03-26 14:03:56 
 */
@Api(tags = "流程节点历史表")
@RestController
@RequestMapping("/lcgl/lcglJdlxb")
public class LcglJdlxbController extends BaseController {
	
	@Autowired
	private ILcglJdlxbService iLcglJdlxbService;

	@ApiOperation(value = "列表")
	@RequiresPermissions("")
	@GetMapping("/list")
	public ResponseBean<Page<LcglJdlxb>> list(Page page) {
		QueryWrapper<LcglJdlxb> queryWrapper = new QueryWrapper<LcglJdlxb>();
		return ResponseBean.Success(iLcglJdlxbService.page(page,queryWrapper));
	}

	@ApiOperation(value = "添加")
	@RequiresPermissions("")
	@PostMapping("/save")
	public ResponseBean save(@RequestBody LcglJdlxb record) {
		if (iLcglJdlxbService.save(record)) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}

	@ApiOperation(value = "去编辑")
	@RequiresPermissions("")
	@GetMapping("/toUpdate")
	public ResponseBean<LcglJdlxb> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
		LcglJdlxb lcglJdlxb = iLcglJdlxbService.getById(id);
		if (ObjectUtil.isNotNull(lcglJdlxb)) {
			return ResponseBean.Success(lcglJdlxb);
		} else {
			return ResponseBean.Fail();
		}
	}


	@ApiOperation(value = "编辑")
	@RequiresPermissions("")
	@PutMapping("/update")
	public ResponseBean update(@RequestBody LcglJdlxb record) {
		if (iLcglJdlxbService.updateById(record)) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}

	@ApiOperation(value = "删除")
	@RequiresPermissions("")
	@DeleteMapping("/remove")
	public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
		if (iLcglJdlxbService.removeByIds(Arrays.asList(ids))) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}
}
