package com.gzxn.ebp.ebp_zbxm.entity;

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
 * @Package: com.gzxn.ebp.ebp_zbxm.entity
 * @ClassName: EbpZbxmQd
 * @Description: 招标项目清单表-实体类
 * @author: CodeBird
 * @date:  2022-03-26 14:01:37 
 */
@ApiModel(value = "招标项目清单表")
@Data
public class EbpZbxmQd extends BaseEntity<EbpZbxmQd> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	@TableId("id")
	private String id;
	/**
	 * 采购包id
	 */
	@ApiModelProperty(value = "采购包id")
	@TableField("cgbid")
	private String cgbid;
	/**
	 * 采购包编号
	 */
	@ApiModelProperty(value = "采购包编号")
	@TableField("cgbbh")
	private String cgbbh;
	/**
	 * 采购包名称
	 */
	@ApiModelProperty(value = "采购包名称")
	@TableField("cgbmc")
	private String cgbmc;
	/**
	 * 招标项目id
	 */
	@ApiModelProperty(value = "招标项目id")
	@TableField("zbxmid")
	private String zbxmid;
	/**
	 * 招标项目编号
	 */
	@ApiModelProperty(value = "招标项目编号")
	@TableField("zbxmbh")
	private String zbxmbh;
	/**
	 * 招标项目名称
	 */
	@ApiModelProperty(value = "招标项目名称")
	@TableField("zbxmmc")
	private String zbxmmc;
	/**
	 * 组织形式
	 */
	@ApiModelProperty(value = "组织形式")
	@TableField("zzxs")
	private String zzxs;
	/**
	 * 采购方式
	 */
	@ApiModelProperty(value = "采购方式")
	@TableField("cgfs")
	private String cgfs;
	/**
	 * 预算金额（元）
	 */
	@ApiModelProperty(value = "预算金额（元）")
	@TableField("ysje")
	private BigDecimal ysje;
	/**
	 * 是否已登记中标结果：0-否、1-是
	 */
	@ApiModelProperty(value = "是否已登记中标结果：0-否、1-是")
	@TableField("is_zbdj")
	private Integer isZbdj;
	/**
	 * 归口部门
	 */
	@ApiModelProperty(value = "归口部门")
	@TableField("manage_depart")
	private String manageDepart;
	/**
	 * 顺序号
	 */
	@ApiModelProperty(value = "顺序号")
	@TableField("sxh")
	private Integer sxh;
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
