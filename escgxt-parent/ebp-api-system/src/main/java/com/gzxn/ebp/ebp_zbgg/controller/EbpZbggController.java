package com.gzxn.ebp.ebp_zbgg.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.ebp_zbgg.entity.EbpZbgg;
import com.gzxn.ebp.ebp_zbgg.service.IEbpZbggService;
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
 * @Package: com.gzxn.ebp.ebp_zbgg.controller
 * @ClassName: EbpZbggController
 * @Description: 招标公告表-控制器
 * @author: CodeBird
 * @date:  2022-03-26 14:02:28 
 */
@Api(tags = "招标公告表")
@RestController
@RequestMapping("/ebp_zbgg/ebpZbgg")
public class EbpZbggController extends BaseController {
	
	@Autowired
	private IEbpZbggService iEbpZbggService;

	@ApiOperation(value = "列表")
	@RequiresPermissions("7000010001")
	@GetMapping("/list")
	public ResponseBean<Page<EbpZbgg>> list(Page page) {
		QueryWrapper<EbpZbgg> queryWrapper = new QueryWrapper<EbpZbgg>();
		return ResponseBean.Success(iEbpZbggService.page(page,queryWrapper));
	}

	@ApiOperation(value = "添加")
	@RequiresPermissions("7000010005")
	@PostMapping("/save")
	public ResponseBean save(@RequestBody EbpZbgg record) {
		if (iEbpZbggService.save(record)) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}

	@ApiOperation(value = "去编辑")
	@RequiresPermissions("7000010010")
	@GetMapping("/toUpdate")
	public ResponseBean<EbpZbgg> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
		EbpZbgg ebpZbgg = iEbpZbggService.getById(id);
		if (ObjectUtil.isNotNull(ebpZbgg)) {
			return ResponseBean.Success(ebpZbgg);
		} else {
			return ResponseBean.Fail();
		}
	}


	@ApiOperation(value = "编辑")
	@RequiresPermissions("7000010010")
	@PutMapping("/update")
	public ResponseBean update(@RequestBody EbpZbgg record) {
		if (iEbpZbggService.updateById(record)) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}

	@ApiOperation(value = "删除")
	@RequiresPermissions("7000010015")
	@DeleteMapping("/remove")
	public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
		if (iEbpZbggService.removeByIds(Arrays.asList(ids))) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}
}
