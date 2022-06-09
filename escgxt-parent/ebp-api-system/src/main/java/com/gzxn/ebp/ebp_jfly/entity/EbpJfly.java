package com.gzxn.ebp.ebp_jfly.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gzxn.core.base.entity.BaseEntity;
import com.gzxn.core.system.annotation.OptionDictionary;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Copyright  2022-03-26 QLH. Tech Ltd. All rights reserved.
 *
 * @Package: com.gzxn.ebp.ebp_jfly.entity
 * @ClassName: EbpJfly
 * @Description: 经费来源表-实体类
 * @author: CodeBird
 * @date: 2022-03-26 14:03:37
 */
@ApiModel(value = "经费来源表")
@Data
public class EbpJfly extends BaseEntity<EbpJfly> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    @TableId("id")
    private String id;
    /**
     * 预算项目名称
     */
    @ApiModelProperty(value = "预算项目名称", required = true)
    @TableField("ysmc")
    private String ysmc;
    /**
     * 预算项目编码
     */
    @ApiModelProperty(value = "预算项目编码", required = true)
    @TableField("ysbm")
    private String ysbm;
    /**
     * 年度
     */
    @ApiModelProperty(value = "年度", required = true)
    @TableField("year")
    private String year;
    /**
     * 经办部门
     */
    @ApiModelProperty(value = "经办部门")
    @TableField("jbbm")
    private String jbbm;
    /**
     * 经办部门编码
     */
    @ApiModelProperty(value = "经办部门编码")
    @TableField("jbbmbm")
    private String jbbmbm;
    /**
     * 经费类型
     */
    @ApiModelProperty(value = "经费类型")
    @TableField("jf_type")
    private String jfType;
    /**
     * 流程状态
     */
    @OptionDictionary(key = "audit-type")
    @ApiModelProperty(value = "流程状态")
    @TableField("bpm_status")
    private String bpmStatus;
    /**
     * 删除状态：0-否、1-是
     */
    @OptionDictionary(key = "yes-or-no")
    @ApiModelProperty(value = "删除状态：0-否、1-是", required = true)
    @TableField("delete_flag")
    private Integer deleteFlag;


    @Override
    protected Serializable pkVal() {
        return id;
    }


}
