package com.gzxn.ebp.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Package: com.gzxn.ebp.system.entity
 * @ClassName: UserRole
 * @Author: CodeBird
 * @Date: 2022-03-06 17:54
 * @Description: 用户角色表-实体类
 */
@ApiModel(value = "用户角色表")
@Data
public class UserRole {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "", required = true)
    @TableId("id")
    private String id;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    @TableField("uid")
    private String uid;
    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    @TableField("rid")
    private String rid;

}
