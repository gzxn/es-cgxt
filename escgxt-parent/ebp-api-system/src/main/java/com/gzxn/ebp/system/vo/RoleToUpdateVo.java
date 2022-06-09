package com.gzxn.ebp.system.vo;

import com.gzxn.ebp.system.entity.Role;
import com.gzxn.ebp.system.entity.RoleDepartment;
import com.gzxn.ebp.system.entity.RolePermission;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Package: com.gzxn.ebp.system.vo
 * @ClassName: RoleToUpdateVo
 * @Author: CodeBird
 * @Date: 2022-03-06 18:05
 * @Description: 角色去编辑Vo
 */
@Data
@ApiModel(value = "角色去编辑Vo")
public class RoleToUpdateVo {
    /**
     * 角色信息
     */
    @ApiModelProperty(value = "角色信息")
    private Role role;
    /**
     * 角色权限
     */
    @ApiModelProperty(value = "角色权限")
    private List<RolePermission> rolePermissions;
    /**
     * 角色部门
     */
    @ApiModelProperty(value = "角色部门")
    private RoleDepartment roleDepartment;
}
