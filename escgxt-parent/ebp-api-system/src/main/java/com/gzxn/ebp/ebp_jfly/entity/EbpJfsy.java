package com.gzxn.ebp.ebp_jfly.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gzxn.core.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Copyright  2022-03-26 QLH. Tech Ltd. All rights reserved.
 * 
 * @Package: com.gzxn.ebp.ebp_jfly.entity
 * @ClassName: EbpJfsy
 * @Description: 经费使用表-实体类
 * @author: CodeBird
 * @date:  2022-03-26 14:03:37 
 */
@ApiModel(value = "经费使用表")
@Data
public class EbpJfsy extends BaseEntity<EbpJfsy> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	@TableId("id")
	private String id;
	/**
	 * 采购申请id
	 */
	@ApiModelProperty(value = "采购申请id", required = true)
	@TableField("cgsqid")
	private String cgsqid;
	/**
	 * 经费来源id
	 */
	@ApiModelProperty(value = "经费来源id")
	@TableField("jflyid")
	private String jflyid;
	/**
	 * 使用金额(元)
	 */
	@ApiModelProperty(value = "使用金额(元)", required = true)
	@TableField("syje")
	private BigDecimal syje;
	/**
	 * 预算编码
	 */
	@ApiModelProperty(value = "预算编码")
	@TableField("ysbm")
	private String ysbm;
	/**
	 * 预算名称
	 */
	@ApiModelProperty(value = "预算名称")
	@TableField("ysmc")
	private String ysmc;
	/**
	 * 经费类型
	 */
	@ApiModelProperty(value = "经费类型")
	@TableField("jf_type")
	private String jfType;
	/**
	 * 是否为年度预算内资金：0-否、1-是
	 */
	@ApiModelProperty(value = "是否为年度预算内资金：0-否、1-是")
	@TableField("is_ndysnzj")
	private Integer isNdysnzj;
	/**
	 * 删除状态：0-否、1-是
	 */
	@ApiModelProperty(value = "删除状态：0-否、1-是", required = true)
	@TableField("delete_flag")
	private Integer deleteFlag;
	 
	 
	 
	@Override
	protected Serializable pkVal() {
				return id;
	}
 

	 
}
