package com.gzxn.ebp.system.vo;

import com.gzxn.ebp.system.entity.Department;
import lombok.Data;

import java.util.List;

/**
 * @Package: com.gzxn.ebp.system.vo
 * @ClassName: DepartmentVo
 * @Author: CodeBird
 * @Date: 2022-03-06 18:04
 * @Description: 部门Vo
 */
@Data
public class DepartmentVo extends Department {

    private List<Department> list;

}
