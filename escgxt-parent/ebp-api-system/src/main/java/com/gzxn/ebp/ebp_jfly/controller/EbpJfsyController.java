package com.gzxn.ebp.ebp_jfly.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.ebp_jfly.entity.EbpJfsy;
import com.gzxn.ebp.ebp_jfly.service.IEbpJfsyService;
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
 * @Package: com.gzxn.ebp.ebp_jfly.controller
 * @ClassName: EbpJfsyController
 * @Description: 经费使用表-控制器
 * @author: CodeBird
 * @date:  2022-03-26 14:03:37 
 */
@Api(tags = "经费使用表")
@RestController
@RequestMapping("/ebp_jfly/ebpJfsy")
public class EbpJfsyController extends BaseController {
	
	@Autowired
	private IEbpJfsyService iEbpJfsyService;

	@ApiOperation(value = "列表")
	@RequiresPermissions("5000020001")
	@GetMapping("/list")
	public ResponseBean<Page<EbpJfsy>> list(Page page) {
		QueryWrapper<EbpJfsy> queryWrapper = new QueryWrapper<EbpJfsy>();
		return ResponseBean.Success(iEbpJfsyService.page(page,queryWrapper));
	}

	@ApiOperation(value = "添加")
	@RequiresPermissions("5000020005")
	@PostMapping("/save")
	public ResponseBean save(@RequestBody EbpJfsy record) {
		if (iEbpJfsyService.save(record)) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}

	@ApiOperation(value = "去编辑")
	@RequiresPermissions("5000020010")
	@GetMapping("/toUpdate")
	public ResponseBean<EbpJfsy> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
		EbpJfsy ebpJfsy = iEbpJfsyService.getById(id);
		if (ObjectUtil.isNotNull(ebpJfsy)) {
			return ResponseBean.Success(ebpJfsy);
		} else {
			return ResponseBean.Fail();
		}
	}


	@ApiOperation(value = "编辑")
	@RequiresPermissions("5000020010")
	@PutMapping("/update")
	public ResponseBean update(@RequestBody EbpJfsy record) {
		if (iEbpJfsyService.updateById(record)) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}

	@ApiOperation(value = "删除")
	@RequiresPermissions("5000020015")
	@DeleteMapping("/remove")
	public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
		if (iEbpJfsyService.removeByIds(Arrays.asList(ids))) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}
}
