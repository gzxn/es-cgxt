package com.gzxn.ebp.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Package: com.gzxn.ebp.system.entity
 * @ClassName: RolePermission
 * @Author: CodeBird
 * @Date: 2022-03-06 17:53
 * @Description: 角色权限表-实体类
 */
@ApiModel(value = "角色权限表")
@Data
public class RolePermission {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "", required = true)
    @TableId("id")
    private String id;
    /**
     * 权限id
     */
    @ApiModelProperty(value = "权限id")
    @TableField("pid")
    private String pid;
    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    @TableField("rid")
    private String rid;

}
