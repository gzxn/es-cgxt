package com.gzxn.ebp.ebp_jfly.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.ebp.ebp_jfly.entity.EbpJfly;
import com.gzxn.ebp.system.entity.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Package: com.gzxn.ebp.ebp_jfly.mapper
 * @ClassName: EbpJflyMapper
 * @Description: 经费来源表-Mapper
 * @author: CodeBird
 * @date: 2022-03-26 14:03:37
 */
public interface EbpJflyMapper extends BaseMapper<EbpJfly> {

    Page selectPageVo(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

    List<Department> selectDepartmentById(@Param("id") String id);

}
