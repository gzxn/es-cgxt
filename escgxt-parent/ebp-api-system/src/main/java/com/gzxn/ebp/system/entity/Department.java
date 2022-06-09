package com.gzxn.ebp.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gzxn.core.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Package: com.gzxn.ebp.system.entity
 * @ClassName: Department
 * @Author: CodeBird
 * @Date: 2022-03-06 17:49
 * @Description: 部门表-实体类
 */
@ApiModel(value = "部门表")
@Data
public class Department extends BaseEntity<Department> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    @TableId("id")
    private String id;
    /**
     * 编号
     */
    @ApiModelProperty(value = "编号", required = true)
    @TableField("code")
    private String code;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", required = true)
    @TableField("name")
    private String name;
    /**
     * 父id
     */
    @ApiModelProperty(value = "父id")
    @TableField("pid")
    private String pid;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", required = true)
    @TableField("sort")
    private Integer sort;
    /**
     * 等级
     */
    @ApiModelProperty(value = "等级", required = true)
    @TableField("level")
    private Integer level;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("remarks")
    private String remarks;

}
