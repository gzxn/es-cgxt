package com.gzxn.ebp.ebp_jfly.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gzxn.ebp.ebp_jfly.entity.EbpJfly;
import com.gzxn.ebp.system.entity.Department;

import java.util.List;

/**
 * @Package: com.gzxn.ebp.ebp_jfly.service
 * @ClassName: EbpJflyService
 * @Description: 经费来源表-服务类接口
 * @author: CodeBird
 * @date: 2022-03-26 14:03:37
 */
public interface IEbpJflyService extends IService<EbpJfly> {

    Page pageVo(Page page, Wrapper wrapper);

    /**
     * 根据id查询部门
     *
     * @param id
     * @return
     */
    List<Department> departmentVo(String id);

}
