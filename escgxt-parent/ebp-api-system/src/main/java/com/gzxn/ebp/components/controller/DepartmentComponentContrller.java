package com.gzxn.ebp.components.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.system.entity.Department;
import com.gzxn.ebp.system.service.IDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.gzxn.ebp.components.controller
 * @ClassName: DepartmentComponentContrller
 * @Author: CodeBird
 * @Date: 2022-03-06 17:46
 * @Description: 部门组件-控制器
 */
@Api(tags = "部门组件-控制器")
@RestController
@RequestMapping("/components/departmentComponent")
public class DepartmentComponentContrller extends BaseController {
    @Autowired
    private IDepartmentService iDepartmentService;

    @ApiOperation(value = "列表")
    @GetMapping("/list")
    public ResponseBean<List<Department>> list() {
        QueryWrapper<Department> queryWrapper = new QueryWrapper<Department>();
        queryWrapper.orderByAsc("level");
        queryWrapper.orderByAsc("sort");
        queryWrapper.orderByAsc("crtm");
        // 查询所有的部门
        List<Department> list = iDepartmentService.list(queryWrapper);
        return ResponseBean.Success(list);
    }

    @ApiOperation(value = "根据ids查询")
    @GetMapping("/getByIds")
    public ResponseBean<List<Department>> getByIds(
            @ApiParam(value = "ids") @RequestParam(name = "ids", required = false) String[] ids) {
        List<Department> departments = null;
        if (ObjectUtil.isNotEmpty(ids)) {
            departments = new ArrayList<Department>();
            for (String id : ids) {
                departments.add(iDepartmentService.getById(id));
            }
        }
        return ResponseBean.Success(departments);
    }

}
