package com.gzxn.ebp.ebp_cgsq.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gzxn.core.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Copyright  2022-03-26 QLH. Tech Ltd. All rights reserved.
 * 
 * @Package: com.gzxn.ebp.ebp_cgsq.entity
 * @ClassName: EbpCgsqQd
 * @Description: 采购申请清单表-实体类
 * @author: CodeBird
 * @date:  2022-03-26 13:57:47 
 */
@ApiModel(value = "采购申请清单表")
@Data
public class EbpCgsqQd extends BaseEntity<EbpCgsqQd> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	@TableId("id")
	private String id;
	/**
	 * 采购项目id
	 */
	@ApiModelProperty(value = "采购项目id")
	@TableField("cgsqid")
	private String cgsqid;
	/**
	 * 流水号
	 */
	@ApiModelProperty(value = "流水号")
	@TableField("hwbh")
	private String hwbh;
	/**
	 * 设备名称
	 */
	@ApiModelProperty(value = "设备名称")
	@TableField("mc")
	private String mc;
	/**
	 * 品目名称
	 */
	@ApiModelProperty(value = "品目名称")
	@TableField("pmmc")
	private String pmmc;
	/**
	 * 品目编码
	 */
	@ApiModelProperty(value = "品目编码")
	@TableField("pmbm")
	private String pmbm;
	/**
	 * 数量
	 */
	@ApiModelProperty(value = "数量")
	@TableField("sl")
	private Integer sl;
	/**
	 * 单位
	 */
	@ApiModelProperty(value = "单位")
	@TableField("dw")
	private String dw;
	/**
	 * 单价(元)
	 */
	@ApiModelProperty(value = "单价(元)")
	@TableField("dj")
	private BigDecimal dj;
	/**
	 * 金额(元)
	 */
	@ApiModelProperty(value = "金额(元)")
	@TableField("je")
	private BigDecimal je;
	/**
	 * 规格型号
	 */
	@ApiModelProperty(value = "规格型号")
	@TableField("ggxh")
	private String ggxh;
	/**
	 * 参数
	 */
	@ApiModelProperty(value = "参数")
	@TableField("cssm")
	private String cssm;
	/**
	 * 申报人
	 */
	@ApiModelProperty(value = "申报人")
	@TableField("sbr")
	private String sbr;
	/**
	 * 申报人用户名
	 */
	@ApiModelProperty(value = "申报人用户名")
	@TableField("sbryhm")
	private String sbryhm;
	/**
	 * 申报部门
	 */
	@ApiModelProperty(value = "申报部门")
	@TableField("sbbm")
	private String sbbm;
	/**
	 * 申报部门编码
	 */
	@ApiModelProperty(value = "申报部门编码")
	@TableField("sbbmmm")
	private String sbbmmm;
	/**
	 * 申报日期
	 */
	@ApiModelProperty(value = "申报日期")
	@TableField("sbrq")
	private Date sbrq;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	@TableField("bz")
	private String bz;
	/**
	 * 附件
	 */
	@ApiModelProperty(value = "附件")
	@TableField("fj")
	private String fj;
	/**
	 * 流程状态
	 */
	@ApiModelProperty(value = "流程状态")
	@TableField("bpm_status")
	private String bpmStatus;
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
	 * 成交单价
	 */
	@ApiModelProperty(value = "成交单价")
	@TableField("cjdj")
	private BigDecimal cjdj;
	/**
	 * 成交总价
	 */
	@ApiModelProperty(value = "成交总价")
	@TableField("cjzj")
	private BigDecimal cjzj;
	/**
	 * 采购执行人
	 */
	@ApiModelProperty(value = "采购执行人")
	@TableField("cgzxr")
	private String cgzxr;
	/**
	 * 是否打包
	 */
	@ApiModelProperty(value = "是否打包")
	@TableField("is_pack")
	private Integer isPack;
	/**
	 * 是否分配任务：0-否、1-是
	 */
	@ApiModelProperty(value = "是否分配任务：0-否、1-是")
	@TableField("is_task")
	private Integer isTask;
	/**
	 * 是否登记结果：0-否、1-是
	 */
	@ApiModelProperty(value = "是否登记结果：0-否、1-是")
	@TableField("is_djjg")
	private Integer isDjjg;
	/**
	 * 是否结果公示：0-否、1-是
	 */
	@ApiModelProperty(value = "是否结果公示：0-否、1-是")
	@TableField("is_jggs")
	private Integer isJggs;
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
