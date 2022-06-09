package com.gzxn.ebp.ebp_cgsq.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gzxn.core.base.entity.BaseEntity;
import com.gzxn.core.system.annotation.OptionDictionary;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Copyright  2022-03-26 QLH. Tech Ltd. All rights reserved.
 * 
 * @Package: com.gzxn.ebp.ebp_cgsq.entity
 * @ClassName: EbpCgsq
 * @Description: 采购申请表-实体类
 * @author: CodeBird
 * @date:  2022-03-26 13:57:47 
 */
@ApiModel(value = "采购申请表")
@Data
public class EbpCgsq extends BaseEntity<EbpCgsq> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	@TableId("id")
	private String id;
	/**
	 * 采购项目编号
	 */
	@ApiModelProperty(value = "采购项目编号", required = true)
	@TableField("cgbh")
	private String cgbh;
	/**
	 * 项目名称
	 */
	@ApiModelProperty(value = "项目名称", required = true)
	@TableField("xmmc")
	private String xmmc;
	/**
	 * 申报部门编号
	 */
	@ApiModelProperty(value = "申报部门编号")
	@TableField("sbbmbh")
	private String sbbmbh;
	/**
	 * 申报部门名称
	 */
	@ApiModelProperty(value = "申报部门名称")
	@TableField("sbbm")
	private String sbbm;
	/**
	 * 申报部门id
	 */
	@ApiModelProperty(value = "申报部门id")
	@TableField("sbbm_id")
	private String sbbmId;
	/**
	 * 预算金额(元)
	 */
	@ApiModelProperty(value = "预算金额(元)")
	@TableField("ysje")
	private BigDecimal ysje;
	/**
	 * 采购类型(货物类/工程类/服务类)
	 */
	@ApiModelProperty(value = "采购类型(货物类/工程类/服务类)")
	@TableField("xmlx")
	private String xmlx;
	/**
	 * 项目负责人用户名
	 */
	@ApiModelProperty(value = "项目负责人用户名")
	@TableField("xmfzryhm")
	private String xmfzryhm;
	/**
	 * 项目负责人
	 */
	@ApiModelProperty(value = "项目负责人")
	@TableField("xmfzr")
	private String xmfzr;
	/**
	 * 联系人
	 */
	@ApiModelProperty(value = "联系人")
	@TableField("lxr")
	private String lxr;
	/**
	 * 联系人用户名
	 */
	@ApiModelProperty(value = "联系人用户名")
	@TableField("lxryhm")
	private String lxryhm;
	/**
	 * 联系人电话
	 */
	@ApiModelProperty(value = "联系人电话")
	@TableField("lxrdh")
	private String lxrdh;
	/**
	 * 申报日期
	 */
	@ApiModelProperty(value = "申报日期")
	@TableField("sbrq")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JSONField(format = "yyyy-MM-dd")
	private Date sbrq;
	/**
	 * 项目状态
	 */
	@ApiModelProperty(value = "项目状态")
	@TableField("xmzt")
	private String xmzt;
	/**
	 * 流程状态
	 */
	@OptionDictionary(key = "audit-type")
	@ApiModelProperty(value = "流程状态")
	@TableField("bpm_status")
	private String bpmStatus;
	/**
	 * 采购申请填报类型
	 */
	@ApiModelProperty(value = "采购申请填报类型")
	@TableField("cgsq_tblx")
	private String cgsqTblx;
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
	 * 经费来源id
	 */
	@ApiModelProperty(value = "经费来源id")
	@TableField("jfly_id")
	private String jflyId;
	/**
	 * 拟申请采购方式
	 */
	@ApiModelProperty(value = "拟申请采购方式")
	@TableField("nsqcgfs")
	private String nsqcgfs;
	/**
	 * 项目类型(教学型/科研型)
	 */
	@ApiModelProperty(value = "项目类型(教学型/科研型)")
	@TableField("cglx_type")
	private String cglxType;
	/**
	 * 采购申请类型(xmlx、cgjh)
	 */
	@ApiModelProperty(value = "采购申请类型(xmlx、cgjh)")
	@TableField("cgsq_type")
	private String cgsqType;
	/**
	 * 当前节点id
	 */
	@ApiModelProperty(value = "当前节点id")
	@TableField(value = "dqjd")
	private String dqjd;
	/**
	 * 当前处理人
	 */
	@ApiModelProperty(value = "当前处理人")
	@TableField("dqclr")
	private String dqclr;
	/**
	 * 当前处理人用户名
	 */
	@ApiModelProperty(value = "当前处理人用户名")
	@TableField("dqclryhm")
	private String dqclryhm;
	/**
	 * 采购执行人
	 */
	@ApiModelProperty(value = "采购执行人")
	@TableField("cgzxr")
	private String cgzxr;
	/**
	 * 是否注销：0-否、1-是
	 */
//	@OptionDictionary(key = "yes-or-no")
	@ApiModelProperty(value = "是否注销：0-否、1-是")
	@TableField("is_zx")
	private Integer isZx;
	/**
	 * 是否变更：0-否、1-是
	 */
//	@OptionDictionary(key = "yes-or-no")
	@ApiModelProperty(value = "是否变更：0-否、1-是")
	@TableField("is_bg")
	private Integer isBg;
	/**
	 * 是否打包分包：0-否、1-是
	 */
//	@OptionDictionary(key = "yes-or-no")
	@ApiModelProperty(value = "是否打包分包：0-否、1-是")
	@TableField("is_package")
	private Integer isPackage;
	/**
	 * 建议采购方式
	 */
	@ApiModelProperty(value = "建议采购方式")
	@TableField("jycgfs")
	private String jycgfs;
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
