package com.gzxn.ebp.ebp_zbxm.entity;

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
 * @Package: com.gzxn.ebp.ebp_zbxm.entity
 * @ClassName: EbpZbxm
 * @Description: 招标项目表-实体类
 * @author: CodeBird
 * @date:  2022-03-26 14:01:37 
 */
@ApiModel(value = "招标项目表")
@Data
public class EbpZbxm extends BaseEntity<EbpZbxm> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	@TableId("id")
	private String id;
	/**
	 * 招标项目编码
	 */
	@ApiModelProperty(value = "招标项目编码", required = true)
	@TableField("zbxmbm")
	private String zbxmbm;
	/**
	 * 招标项目名称
	 */
	@ApiModelProperty(value = "招标项目名称", required = true)
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
	 * 预算金额(元)
	 */
	@ApiModelProperty(value = "预算金额(元)")
	@TableField("ysje")
	private BigDecimal ysje;
	/**
	 * 项目负责人
	 */
	@ApiModelProperty(value = "项目负责人")
	@TableField("cgsq_xmfzr")
	private String cgsqXmfzr;
	/**
	 * 项目负责人用户名
	 */
	@ApiModelProperty(value = "项目负责人用户名")
	@TableField("cgsq_xmfzryhm")
	private String cgsqXmfzryhm;
	/**
	 * 项目经办人
	 */
	@ApiModelProperty(value = "项目经办人")
	@TableField("cgsq_lxr")
	private String cgsqLxr;
	/**
	 * 项目经办人用户名
	 */
	@ApiModelProperty(value = "项目经办人用户名")
	@TableField("cgsq_lxryhm")
	private String cgsqLxryhm;
	/**
	 * 申请时间
	 */
	@ApiModelProperty(value = "申请时间")
	@TableField("sqsj")
	private Date sqsj;
	/**
	 * 采购执行人
	 */
	@ApiModelProperty(value = "采购执行人")
	@TableField("xmfzr")
	private String xmfzr;
	/**
	 * 采购执行人id
	 */
	@ApiModelProperty(value = "采购执行人id")
	@TableField("xmfzrid")
	private String xmfzrid;
	/**
	 * 代理委托编号
	 */
	@ApiModelProperty(value = "代理委托编号")
	@TableField("dlwtbh")
	private String dlwtbh;
	/**
	 * 是否委托代理
	 */
	@ApiModelProperty(value = "是否委托代理", required = true)
	@TableField("is_wt")
	private Integer isWt;
	/**
	 * 是否校内
	 */
	@ApiModelProperty(value = "是否校内")
	@TableField("is_school")
	private Integer isSchool;
	/**
	 * 是否已登记中标结果
	 */
	@ApiModelProperty(value = "是否已登记中标结果")
	@TableField("is_zbjg")
	private Integer isZbjg;
	/**
	 * 是否已结果公示
	 */
	@ApiModelProperty(value = "是否已结果公示")
	@TableField("is_jggs")
	private Integer isJggs;
	/**
	 * 是否已发布招标公告
	 */
	@ApiModelProperty(value = "是否已发布招标公告")
	@TableField("is_issue")
	private Integer isIssue;
	/**
	 * 是否已抽取代理机构
	 */
	@ApiModelProperty(value = "是否已抽取代理机构")
	@TableField("is_cqdl")
	private Integer isCqdl;
	/**
	 * 是否已上传采购结果
	 */
	@ApiModelProperty(value = "是否已上传采购结果")
	@TableField("is_cgjg")
	private Integer isCgjg;
	/**
	 * 是否招标失败
	 */
	@ApiModelProperty(value = "是否招标失败")
	@TableField("is_failed")
	private Integer isFailed;
	/**
	 * 是否已提交采购文件
	 */
	@ApiModelProperty(value = "是否已提交采购文件")
	@TableField("is_commit_purfile")
	private Integer isCommitPurfile;
	/**
	 * 是否已政府批文
	 */
	@ApiModelProperty(value = "是否已政府批文")
	@TableField("is_zfpw")
	private Integer isZfpw;
	/**
	 * 是否记录开标
	 */
	@ApiModelProperty(value = "是否记录开标")
	@TableField("is_record_bidding")
	private Integer isRecordBidding;
	/**
	 * 是否投标报名
	 */
	@ApiModelProperty(value = "是否投标报名")
	@TableField("is_tbbm")
	private Integer isTbbm;
	/**
	 * 是否结果确认
	 */
	@ApiModelProperty(value = "是否结果确认")
	@TableField("is_jgqr")
	private Integer isJgqr;
	/**
	 * 是否资格审查
	 */
	@ApiModelProperty(value = "是否资格审查")
	@TableField("is_zgsc")
	private Integer isZgsc;
	/**
	 * 是否开标评审
	 */
	@ApiModelProperty(value = "是否开标评审")
	@TableField("is_kbps")
	private Integer isKbps;
	/**
	 * 申请人
	 */
	@ApiModelProperty(value = "申请人")
	@TableField("sqr")
	private String sqr;
	/**
	 * 申请人用户名
	 */
	@ApiModelProperty(value = "申请人用户名")
	@TableField("sqryhm")
	private String sqryhm;
	/**
	 * 经费来源id
	 */
	@ApiModelProperty(value = "经费来源id")
	@TableField("jflyid")
	private String jflyid;
	/**
	 * 是否符合开标
	 */
	@ApiModelProperty(value = "是否符合开标")
	@TableField("is_fhkb")
	private Integer isFhkb;
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
