package com.gzxn.ebp.system.dto;

import com.gzxn.ebp.system.entity.Role;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @Package: com.gzxn.ebp.system.dto
 * @ClassName: RoleDto
 * @Author: CodeBird
 * @Date: 2022-03-06 17:56
 * @Description: 角色Dto
 */
@ApiModel(value = "角色Dto")
@Data
public class RoleDto extends Role {

    private List<String> permissionIds;

    private String departmentId;

}


