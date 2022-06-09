package com.gzxn.ebp.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gzxn.core.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Package: com.gzxn.ebp.system.entity
 * @ClassName: Dictionarydata
 * @Author: CodeBird
 * @Date: 2022-03-06 17:50
 * @Description: 数据字典数据-实体类
 */
@ApiModel(value = "数据字典数据")
@Data
public class Dictionarydata extends BaseEntity<Dictionarydata> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    @TableId("id")
    private String id;
    /**
     * 数据字典id
     */
    @ApiModelProperty(value = "数据字典id", required = true)
    @TableField("did")
    private String did;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", required = true)
    @TableField("dkey")
    private String dkey;
    /**
     * 数据值
     */
    @ApiModelProperty(value = "数据值", required = true)
    @TableField("dvalue")
    private String dvalue;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private Integer sort;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @TableField("description")
    private String description;
    /**
     * 是否禁用：1-是、2-否
     */
    @ApiModelProperty(value = "是否禁用：1-是、2-否", required = true)
    @TableField("isdisable")
    private Integer isdisable;

}
