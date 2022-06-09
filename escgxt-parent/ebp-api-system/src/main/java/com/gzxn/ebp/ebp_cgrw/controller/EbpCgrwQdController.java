package com.gzxn.ebp.ebp_cgrw.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.ebp_cgrw.entity.EbpCgrwQd;
import com.gzxn.ebp.ebp_cgrw.service.IEbpCgrwQdService;
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
 * @Package: com.gzxn.ebp.ebp_cgrw.controller
 * @ClassName: EbpCgrwQdController
 * @Description: 采购任务清单表-控制器
 * @author: CodeBird
 * @date:  2022-03-26 14:01:01 
 */
@Api(tags = "采购任务清单表")
@RestController
@RequestMapping("/ebp_cgrw/ebpCgrwQd")
public class EbpCgrwQdController extends BaseController {
	
	@Autowired
	private IEbpCgrwQdService iEbpCgrwQdService;

	@ApiOperation(value = "列表")
	@RequiresPermissions("2000020001")
	@GetMapping("/list")
	public ResponseBean<Page<EbpCgrwQd>> list(Page page) {
		QueryWrapper<EbpCgrwQd> queryWrapper = new QueryWrapper<EbpCgrwQd>();
		return ResponseBean.Success(iEbpCgrwQdService.page(page,queryWrapper));
	}

	@ApiOperation(value = "添加")
	@RequiresPermissions("2000020005")
	@PostMapping("/save")
	public ResponseBean save(@RequestBody EbpCgrwQd record) {
		if (iEbpCgrwQdService.save(record)) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}

	@ApiOperation(value = "去编辑")
	@RequiresPermissions("2000020010")
	@GetMapping("/toUpdate")
	public ResponseBean<EbpCgrwQd> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
		EbpCgrwQd ebpCgrwQd = iEbpCgrwQdService.getById(id);
		if (ObjectUtil.isNotNull(ebpCgrwQd)) {
			return ResponseBean.Success(ebpCgrwQd);
		} else {
			return ResponseBean.Fail();
		}
	}


	@ApiOperation(value = "编辑")
	@RequiresPermissions("2000020010")
	@PutMapping("/update")
	public ResponseBean update(@RequestBody EbpCgrwQd record) {
		if (iEbpCgrwQdService.updateById(record)) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}

	@ApiOperation(value = "删除")
	@RequiresPermissions("2000020015")
	@DeleteMapping("/remove")
	public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
		if (iEbpCgrwQdService.removeByIds(Arrays.asList(ids))) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}
}
