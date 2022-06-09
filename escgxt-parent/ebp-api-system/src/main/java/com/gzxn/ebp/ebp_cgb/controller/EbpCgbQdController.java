package com.gzxn.ebp.ebp_cgb.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.ebp_cgb.entity.EbpCgbQd;
import com.gzxn.ebp.ebp_cgb.service.IEbpCgbQdService;
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
 * @Package: com.gzxn.ebp.ebp_cgb.controller
 * @ClassName: EbpCgbQdController
 * @Description: 采购包清单-控制器
 * @author: CodeBird
 * @date:  2022-03-26 13:59:18 
 */
@Api(tags = "采购包清单")
@RestController
@RequestMapping("/ebp_cgb/ebpCgbQd")
public class EbpCgbQdController extends BaseController {
	
	@Autowired
	private IEbpCgbQdService iEbpCgbQdService;

	@ApiOperation(value = "列表")
	@RequiresPermissions("1000020001")
	@GetMapping("/list")
	public ResponseBean<Page<EbpCgbQd>> list(Page page) {
		QueryWrapper<EbpCgbQd> queryWrapper = new QueryWrapper<EbpCgbQd>();
		return ResponseBean.Success(iEbpCgbQdService.page(page,queryWrapper));
	}

	@ApiOperation(value = "添加")
	@RequiresPermissions("1000020005")
	@PostMapping("/save")
	public ResponseBean save(@RequestBody EbpCgbQd record) {
		if (iEbpCgbQdService.save(record)) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}

	@ApiOperation(value = "去编辑")
	@RequiresPermissions("1000020010")
	@GetMapping("/toUpdate")
	public ResponseBean<EbpCgbQd> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
		EbpCgbQd ebpCgbQd = iEbpCgbQdService.getById(id);
		if (ObjectUtil.isNotNull(ebpCgbQd)) {
			return ResponseBean.Success(ebpCgbQd);
		} else {
			return ResponseBean.Fail();
		}
	}


	@ApiOperation(value = "编辑")
	@RequiresPermissions("1000020010")
	@PutMapping("/update")
	public ResponseBean update(@RequestBody EbpCgbQd record) {
		if (iEbpCgbQdService.updateById(record)) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}

	@ApiOperation(value = "删除")
	@RequiresPermissions("1000020015")
	@DeleteMapping("/remove")
	public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
		if (iEbpCgbQdService.removeByIds(Arrays.asList(ids))) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}
}
