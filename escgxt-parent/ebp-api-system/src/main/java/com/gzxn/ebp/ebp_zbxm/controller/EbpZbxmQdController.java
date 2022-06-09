package com.gzxn.ebp.ebp_zbxm.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.ebp_zbxm.entity.EbpZbxmQd;
import com.gzxn.ebp.ebp_zbxm.service.IEbpZbxmQdService;
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
 * @Package: com.gzxn.ebp.ebp_zbxm.controller
 * @ClassName: EbpZbxmQdController
 * @Description: 招标项目清单表-控制器
 * @author: CodeBird
 * @date:  2022-03-26 14:01:37 
 */
@Api(tags = "招标项目清单表")
@RestController
@RequestMapping("/ebp_zbxm/ebpZbxmQd")
public class EbpZbxmQdController extends BaseController {
	
	@Autowired
	private IEbpZbxmQdService iEbpZbxmQdService;

	@ApiOperation(value = "列表")
	@RequiresPermissions("9000020001")
	@GetMapping("/list")
	public ResponseBean<Page<EbpZbxmQd>> list(Page page) {
		QueryWrapper<EbpZbxmQd> queryWrapper = new QueryWrapper<EbpZbxmQd>();
		return ResponseBean.Success(iEbpZbxmQdService.page(page,queryWrapper));
	}

	@ApiOperation(value = "添加")
	@RequiresPermissions("9000020005")
	@PostMapping("/save")
	public ResponseBean save(@RequestBody EbpZbxmQd record) {
		if (iEbpZbxmQdService.save(record)) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}

	@ApiOperation(value = "去编辑")
	@RequiresPermissions("9000020010")
	@GetMapping("/toUpdate")
	public ResponseBean<EbpZbxmQd> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
		EbpZbxmQd ebpZbxmQd = iEbpZbxmQdService.getById(id);
		if (ObjectUtil.isNotNull(ebpZbxmQd)) {
			return ResponseBean.Success(ebpZbxmQd);
		} else {
			return ResponseBean.Fail();
		}
	}


	@ApiOperation(value = "编辑")
	@RequiresPermissions("9000020010")
	@PutMapping("/update")
	public ResponseBean update(@RequestBody EbpZbxmQd record) {
		if (iEbpZbxmQdService.updateById(record)) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}

	@ApiOperation(value = "删除")
	@RequiresPermissions("9000020015")
	@DeleteMapping("/remove")
	public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
		if (iEbpZbxmQdService.removeByIds(Arrays.asList(ids))) {
			return ResponseBean.Success();
		} else {
			return ResponseBean.Fail();
		}
	}
}
