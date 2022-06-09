package com.gzxn.ebp.lcgl.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.lcgl.entity.LcglJdb;
import com.gzxn.ebp.lcgl.service.ILcglJdbService;
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
 * @ClassName: LcglJdbController
 * @Description: 流程节点表-控制器
 * @author: CodeBird
 * @date:  2022-03-26 14:03:56 
 */
@Api(tags = "流程节点表")
@RestController
@RequestMapping("/lcgl/lcglJdb")
public class LcglJdbController extends BaseController {
	
	@Autowired
	private ILcglJdbService iLcglJdbService;

	@ApiOperation(value = "列表")
	@RequiresPermissions("")
	@GetMapping("/list")
	public ResponseBean<Page<LcglJdb>> list(Page page) {
		QueryWrapper<LcglJdb> queryWrapper = new QueryWrapper<LcglJdb>();
		return ResponseBean.Success(iLcglJdbService.page(page,queryWrapper));
	}

	@ApiOperation(value = "添加")
	@RequiresPermissions("")
	@PostMapping("/save")
	public ResponseBean save(@RequestBody LcglJdb record) {
		if (iLcglJdbService.save(record)) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}

	@ApiOperation(value = "去编辑")
	@RequiresPermissions("")
	@GetMapping("/toUpdate")
	public ResponseBean<LcglJdb> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
		LcglJdb lcglJdb = iLcglJdbService.getById(id);
		if (ObjectUtil.isNotNull(lcglJdb)) {
			return ResponseBean.Success(lcglJdb);
		} else {
			return ResponseBean.Fail();
		}
	}


	@ApiOperation(value = "编辑")
	@RequiresPermissions("")
	@PutMapping("/update")
	public ResponseBean update(@RequestBody LcglJdb record) {
		if (iLcglJdbService.updateById(record)) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}

	@ApiOperation(value = "删除")
	@RequiresPermissions("")
	@DeleteMapping("/remove")
	public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
		if (iLcglJdbService.removeByIds(Arrays.asList(ids))) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}
}
