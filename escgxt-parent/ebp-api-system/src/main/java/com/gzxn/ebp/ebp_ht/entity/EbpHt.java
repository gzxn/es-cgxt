package com.gzxn.ebp.ebp_ht.entity;

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
 * 
 * @Package: com.gzxn.ebp.ebp_ht.entity
 * @ClassName: EbpHt
 * @Description: 合同表-实体类
 * @author: CodeBird
 * @date:  2022-03-26 14:02:50 
 */
@ApiModel(value = "合同表")
@Data
public class EbpHt extends BaseEntity<EbpHt> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	@TableId("id")
	private String id;
	/**
	 * 合同编号
	 */
	@ApiModelProperty(value = "合同编号", required = true)
	@TableField("htbh")
	private String htbh;
	/**
	 * 合同名称
	 */
	@ApiModelProperty(value = "合同名称", required = true)
	@TableField("htmc")
	private String htmc;
	/**
	 * 采购项目
	 */
	@ApiModelProperty(value = "采购项目")
	@TableField("cgjg")
	private String cgjg;
	/**
	 * 合同金额(元)
	 */
	@ApiModelProperty(value = "合同金额(元)")
	@TableField("htje")
	private BigDecimal htje;
	/**
	 * 乙方
	 */
	@ApiModelProperty(value = "乙方")
	@TableField("cjgys")
	private String cjgys;
	/**
	 * 乙方联系人手机
	 */
	@ApiModelProperty(value = "乙方联系人手机")
	@TableField("gyslxdh")
	private String gyslxdh;
	/**
	 * 乙方联系人
	 */
	@ApiModelProperty(value = "乙方联系人")
	@TableField("gyslxr")
	private String gyslxr;
	/**
	 * 结果id
	 */
	@ApiModelProperty(value = "结果id")
	@TableField("jgid")
	private String jgid;
	/**
	 * 合同类型
	 */
	@ApiModelProperty(value = "合同类型")
	@TableField("htlx")
	private String htlx;
	/**
	 * 质保金(元)
	 */
	@ApiModelProperty(value = "质保金(元)")
	@TableField("zbj")
	private BigDecimal zbj;
	/**
	 * 质保时间(月)
	 */
	@ApiModelProperty(value = "质保时间(月)")
	@TableField("zbsj")
	private Date zbsj;
	/**
	 * 上传扫描件
	 */
	@ApiModelProperty(value = "上传扫描件")
	@TableField("scsmj")
	private String scsmj;
	/**
	 * 丙方
	 */
	@ApiModelProperty(value = "丙方")
	@TableField("bf")
	private String bf;
	/**
	 * 甲方联系人手机
	 */
	@ApiModelProperty(value = "甲方联系人手机")
	@TableField("jflxfs")
	private String jflxfs;
	/**
	 * 甲方联系人
	 */
	@ApiModelProperty(value = "甲方联系人")
	@TableField("jflxr")
	private String jflxr;
	/**
	 * 丙方联系人手机
	 */
	@ApiModelProperty(value = "丙方联系人手机")
	@TableField("bflxrfs")
	private String bflxrfs;
	/**
	 * 丙方联系人
	 */
	@ApiModelProperty(value = "丙方联系人")
	@TableField("bflxr")
	private String bflxr;
	/**
	 * 甲方
	 */
	@ApiModelProperty(value = "甲方")
	@TableField("jfht")
	private String jfht;
	/**
	 * 历史处理人
	 */
	@ApiModelProperty(value = "历史处理人")
	@TableField("lsclryhm")
	private String lsclryhm;
	/**
	 * 当前节点id
	 */
	@ApiModelProperty(value = "当前节点id")
	@TableField("dqjdid")
	private String dqjdid;
	/**
	 * 当前处理人id
	 */
	@ApiModelProperty(value = "当前处理人id")
	@TableField("dqclrid")
	private String dqclrid;
	/**
	 * 合同主要内容
	 */
	@ApiModelProperty(value = "合同主要内容")
	@TableField("content")
	private String content;
	/**
	 * 经费来源
	 */
	@ApiModelProperty(value = "经费来源")
	@TableField("jfly")
	private String jfly;
	/**
	 * 项目负责人
	 */
	@ApiModelProperty(value = "项目负责人")
	@TableField("xmfzr")
	private String xmfzr;
	/**
	 * 项目负责人电话
	 */
	@ApiModelProperty(value = "项目负责人电话")
	@TableField("xmfzrdh")
	private String xmfzrdh;
	/**
	 * 项目负责人id
	 */
	@ApiModelProperty(value = "项目负责人id")
	@TableField("xmfzrid")
	private String xmfzrid;
	/**
	 * 项目负责人部门编号
	 */
	@ApiModelProperty(value = "项目负责人部门编号")
	@TableField("xmfzrbmbh")
	private String xmfzrbmbh;
	/**
	 * 项目负责人部门
	 */
	@ApiModelProperty(value = "项目负责人部门")
	@TableField("xmfzrbm")
	private String xmfzrbm;
	/**
	 * 供应商id
	 */
	@ApiModelProperty(value = "供应商id")
	@TableField("gysid")
	private String gysid;
	/**
	 * 合同执行人用户名
	 */
	@ApiModelProperty(value = "合同执行人用户名")
	@TableField("ht_username")
	private String htUsername;
	/**
	 * 合同执行人
	 */
	@ApiModelProperty(value = "合同执行人")
	@TableField("ht_user")
	private String htUser;
	/**
	 * 上传终稿时间
	 */
	@ApiModelProperty(value = "上传终稿时间")
	@TableField("zhonggao_date")
	private Date zhonggaoDate;
	/**
	 * 是否有质保金：0-否、1-是
	 */
	@ApiModelProperty(value = "是否有质保金：0-否、1-是")
	@TableField("is_zbj")
	private Integer isZbj;
	/**
	 * 归口管理部门
	 */
	@ApiModelProperty(value = "归口管理部门")
	@TableField("gkglbm")
	private String gkglbm;
	/**
	 * 归口管理部门编号
	 */
	@ApiModelProperty(value = "归口管理部门编号")
	@TableField("gkglbmbh")
	private String gkglbmbh;
	/**
	 * 审核完成时间
	 */
	@ApiModelProperty(value = "审核完成时间")
	@TableField("audit_date")
	private Date auditDate;
	/**
	 * 是否归档：0-否、1-是
	 */
	@ApiModelProperty(value = "是否归档：0-否、1-是")
	@TableField("is_gd")
	private Integer isGd;
	/**
	 * 是否完成付款：0-否、1-是
	 */
	@ApiModelProperty(value = "是否完成付款：0-否、1-是")
	@TableField("is_fk")
	private Integer isFk;
	/**
	 * 掌印人
	 */
	@ApiModelProperty(value = "掌印人")
	@TableField("zyr")
	private String zyr;
	/**
	 * 是有用印：0-否、1-是
	 */
	@ApiModelProperty(value = "是有用印：0-否、1-是")
	@TableField("is_yy")
	private Integer isYy;
	/**
	 * 用印时间
	 */
	@ApiModelProperty(value = "用印时间")
	@TableField("yy_time")
	private Date yyTime;
	/**
	 * 掌印人用户名
	 */
	@ApiModelProperty(value = "掌印人用户名")
	@TableField("zyr_yhm")
	private String zyrYhm;
	/**
	 * 采购类型
	 */
	@ApiModelProperty(value = "采购类型")
	@TableField("cglx")
	private String cglx;
	/**
	 * 计价原则
	 */
	@ApiModelProperty(value = "计价原则")
	@TableField("jjyz")
	private String jjyz;
	/**
	 * 项目类型
	 */
	@ApiModelProperty(value = "项目类型")
	@TableField("xmlx")
	private String xmlx;
	/**
	 * 合同价款
	 */
	@ApiModelProperty(value = "合同价款")
	@TableField("htjk")
	private String htjk;
	/**
	 * 用印次数
	 */
	@ApiModelProperty(value = "用印次数")
	@TableField("yycs")
	private Integer yycs;
	/**
	 * 审计确认归档：0-否、1-是
	 */
	@ApiModelProperty(value = "审计确认归档：0-否、1-是")
	@TableField("gd_sjqr")
	private Integer gdSjqr;
	/**
	 * 合同类别
	 */
	@ApiModelProperty(value = "合同类别")
	@TableField("contract_type")
	private String contractType;
	/**
	 * 科研方向
	 */
	@ApiModelProperty(value = "科研方向")
	@TableField("kyfx")
	private String kyfx;
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
