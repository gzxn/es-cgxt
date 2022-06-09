package com.gzxn.ebp.ebp_cgb.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.ebp_cgb.entity.EbpCgb;
import com.gzxn.ebp.ebp_cgb.service.IEbpCgbService;
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
 * @ClassName: EbpCgbController
 * @Description: 采购包表-控制器
 * @author: CodeBird
 * @date:  2022-03-26 13:59:18 
 */
@Api(tags = "采购包表")
@RestController
@RequestMapping("/ebp_cgb/ebpCgb")
public class EbpCgbController extends BaseController {
	
	@Autowired
	private IEbpCgbService iEbpCgbService;

	@ApiOperation(value = "列表")
	@RequiresPermissions("1000010001")
	@GetMapping("/list")
	public ResponseBean<Page<EbpCgb>> list(@ApiParam(value = "采购包编号") @RequestParam(name = "cgbbh",required = false) String cgbbh,
										   @ApiParam(value = "采购包名称") @RequestParam(name = "cgbmc",required = false) String cgbmc,
										   Page page) {
		QueryWrapper<EbpCgb> queryWrapper = new QueryWrapper<EbpCgb>();
		if (StrUtil.isNotBlank(cgbbh)){
			queryWrapper.like("cgbbh",cgbbh);
		}
		if (StrUtil.isNotBlank(cgbmc)){
			queryWrapper.like("cgbmc",cgbmc);
		}
		return ResponseBean.Success(iEbpCgbService.page(page,queryWrapper));
	}

	@ApiOperation(value = "添加")
	@RequiresPermissions("1000010005")
	@PostMapping("/save")
	public ResponseBean save(@RequestBody EbpCgb record) {
		if (iEbpCgbService.save(record)) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}

	@ApiOperation(value = "去编辑")
	@RequiresPermissions("1000010010")
	@GetMapping("/toUpdate")
	public ResponseBean<EbpCgb> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
		EbpCgb ebpCgb = iEbpCgbService.getById(id);
		if (ObjectUtil.isNotNull(ebpCgb)) {
			return ResponseBean.Success(ebpCgb);
		} else {
			return ResponseBean.Fail();
		}
	}


	@ApiOperation(value = "编辑")
	@RequiresPermissions("1000010010")
	@PutMapping("/update")
	public ResponseBean update(@RequestBody EbpCgb record) {
		if (iEbpCgbService.updateById(record)) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}

	@ApiOperation(value = "删除")
	@RequiresPermissions("1000010015")
	@DeleteMapping("/remove")
	public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
		if (iEbpCgbService.removeByIds(Arrays.asList(ids))) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}
}
