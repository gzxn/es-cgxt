package com.gzxn.ebp.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Package: com.gzxn.ebp.system.entity
 * @ClassName: RoleDepartment
 * @Author: CodeBird
 * @Date: 2022-03-06 17:52
 * @Description: 角色部门表-实体类
 */
@ApiModel(value = "角色部门表")
@Data
public class RoleDepartment {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "", required = true)
    @TableId("id")
    private String id;
    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id")
    @TableField("did")
    private String did;
    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    @TableField("rid")
    private String rid;

}
