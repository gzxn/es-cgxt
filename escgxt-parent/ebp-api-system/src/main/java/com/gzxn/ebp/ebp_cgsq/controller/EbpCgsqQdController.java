package com.gzxn.ebp.ebp_cgsq.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.ebp_cgsq.entity.EbpCgsqQd;
import com.gzxn.ebp.ebp_cgsq.service.IEbpCgsqQdService;
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
 * @Package: com.gzxn.ebp.ebp_cgsq.controller
 * @ClassName: EbpCgsqQdController
 * @Description: 采购申请清单表-控制器
 * @author: CodeBird
 * @date:  2022-03-26 13:57:47 
 */
@Api(tags = "采购申请清单表")
@RestController
@RequestMapping("/ebp_cgsq/ebpCgsqQd")
public class EbpCgsqQdController extends BaseController {
	
	@Autowired
	private IEbpCgsqQdService iEbpCgsqQdService;

	@ApiOperation(value = "列表")
	@RequiresPermissions("3000020001")
	@GetMapping("/list")
	public ResponseBean<Page<EbpCgsqQd>> list(Page page) {
		QueryWrapper<EbpCgsqQd> queryWrapper = new QueryWrapper<EbpCgsqQd>();
		return ResponseBean.Success(iEbpCgsqQdService.page(page,queryWrapper));
	}

	@ApiOperation(value = "添加")
	@RequiresPermissions("3000020005")
	@PostMapping("/save")
	public ResponseBean save(@RequestBody EbpCgsqQd record) {
		if (iEbpCgsqQdService.save(record)) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}

	@ApiOperation(value = "去编辑")
	@RequiresPermissions("3000020010")
	@GetMapping("/toUpdate")
	public ResponseBean<EbpCgsqQd> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
		EbpCgsqQd ebpCgsqQd = iEbpCgsqQdService.getById(id);
		if (ObjectUtil.isNotNull(ebpCgsqQd)) {
			return ResponseBean.Success(ebpCgsqQd);
		} else {
			return ResponseBean.Fail();
		}
	}


	@ApiOperation(value = "编辑")
	@RequiresPermissions("3000020010")
	@PutMapping("/update")
	public ResponseBean update(@RequestBody EbpCgsqQd record) {
		if (iEbpCgsqQdService.updateById(record)) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}

	@ApiOperation(value = "删除")
	@RequiresPermissions("3000020015")
	@DeleteMapping("/remove")
	public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
		if (iEbpCgsqQdService.removeByIds(Arrays.asList(ids))) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}
}
