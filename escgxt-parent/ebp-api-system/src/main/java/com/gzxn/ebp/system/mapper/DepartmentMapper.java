package com.gzxn.ebp.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gzxn.ebp.system.entity.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Package: com.gzxn.ebp.system.mapper
 * @ClassName: DepartmentMapper
 * @Author: CodeBird
 * @Date: 2022-03-06 18:00
 * @Description: 部门表-Mapper
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> selectByUid(@Param("uid")String uid);


}
