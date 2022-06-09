package com.gzxn.ebp.ebp_zbjg.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gzxn.core.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @Package: com.gzxn.ebp.ebp_zbjg.entity
 * @ClassName: EbpZbjg
 * @Description: 招标结果表-实体类
 * @author: CodeBird
 * @date:  2022-03-26 14:02:01 
 */
@ApiModel(value = "招标结果表")
@Data
public class EbpZbjg extends BaseEntity<EbpZbjg> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	@TableId("id")
	private String id;
	/**
	 * 采购结果
	 */
	@ApiModelProperty(value = "采购结果")
	@TableField("cgjg")
	private String cgjg;
	/**
	 * 采购编号
	 */
	@ApiModelProperty(value = "采购编号")
	@TableField("jgbh")
	private String jgbh;
	/**
	 * 采购项目名称
	 */
	@ApiModelProperty(value = "采购项目名称")
	@TableField("xmmc")
	private String xmmc;
	/**
	 * 采购供应商
	 */
	@ApiModelProperty(value = "采购供应商")
	@TableField("cjgys")
	private String cjgys;
	/**
	 * 采购金额(元)
	 */
	@ApiModelProperty(value = "采购金额(元)")
	@TableField("cjjg")
	private BigDecimal cjjg;
	/**
	 * 供应商联系电话
	 */
	@ApiModelProperty(value = "供应商联系电话")
	@TableField("gyslxdh")
	private String gyslxdh;
	/**
	 * 供应商联系人
	 */
	@ApiModelProperty(value = "供应商联系人")
	@TableField("gyslxr")
	private String gyslxr;
	/**
	 * 采购日期
	 */
	@ApiModelProperty(value = "采购日期")
	@TableField("cjrq")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JSONField(format = "yyyy-MM-dd")
	private Date cjrq;
	/**
	 * 登记时间
	 */
	@ApiModelProperty(value = "登记时间")
	@TableField("djsj")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JSONField(format = "yyyy-MM-dd")
	private Date djsj;
	/**
	 * 中标结果通知书
	 */
	@ApiModelProperty(value = "中标结果通知书")
	@TableField("zbjgtzs")
	private String zbjgtzs;
	/**
	 * 采购结果类型
	 */
	@ApiModelProperty(value = "采购结果类型")
	@TableField("jglx")
	private String jglx;
	/**
	 * 招标id
	 */
	@ApiModelProperty(value = "招标id")
	@TableField("zbid")
	private String zbid;
	/**
	 * 设备id
	 */
	@ApiModelProperty(value = "设备id")
	@TableField("sbid")
	private String sbid;
	/**
	 * 采购包id
	 */
	@ApiModelProperty(value = "采购包id")
	@TableField("cgbid")
	private String cgbid;
	/**
	 * 供应商id
	 */
	@ApiModelProperty(value = "供应商id")
	@TableField("gysid")
	private String gysid;
	/**
	 * 采购任务id
	 */
	@ApiModelProperty(value = "采购任务id")
	@TableField("cgrwid")
	private String cgrwid;
	/**
	 * 确认结果
	 */
	@ApiModelProperty(value = "确认结果")
	@TableField("confirm_result")
	private String confirmResult;
	/**
	 * 是否已确认：0-否、1-是
	 */
	@ApiModelProperty(value = "是否已确认：0-否、1-是")
	@TableField("is_confirm")
	private Integer isConfirm;
	/**
	 * 采购方式
	 */
	@ApiModelProperty(value = "采购方式")
	@TableField("cgfs")
	private String cgfs;
	/**
	 * 采购状态
	 */
	@ApiModelProperty(value = "采购状态")
	@TableField("cg_status")
	private String cgStatus;
	/**
	 * 合同用户电话
	 */
	@ApiModelProperty(value = "合同用户电话")
	@TableField("ht_userdh")
	private String htUserdh;
	/**
	 * 是否需要签订合同：0-否、1-是
	 */
	@ApiModelProperty(value = "是否需要签订合同：0-否、1-是")
	@TableField("is_qdht")
	private Integer isQdht;
	/**
	 * 投标单位
	 */
	@ApiModelProperty(value = "投标单位")
	@TableField("tbdw")
	private String tbdw;
	/**
	 * 是否到货：0-否、1-是
	 */
	@ApiModelProperty(value = "是否到货：0-否、1-是")
	@TableField("is_dh")
	private Integer isDh;
	/**
	 * 是否发起验收申请：0-否、1-是
	 */
	@ApiModelProperty(value = "是否发起验收申请：0-否、1-是")
	@TableField("is_apply")
	private Integer isApply;
	/**
	 * 是否需要登记验收：0-否、1-是
	 */
	@ApiModelProperty(value = "是否需要登记验收：0-否、1-是")
	@TableField("is_djys")
	private Integer isDjys;
	/**
	 * 是否完成付款：0-否、1-是
	 */
	@ApiModelProperty(value = "是否完成付款：0-否、1-是")
	@TableField("is_fk")
	private Integer isFk;
	/**
	 * 是否提交登记：0-否、1-是
	 */
	@ApiModelProperty(value = "是否提交登记：0-否、1-是")
	@TableField("is_submit")
	private Integer isSubmit;
	/**
	 * 是否完成提交登记：0-否、1-是
	 */
	@ApiModelProperty(value = "是否完成提交登记：0-否、1-是")
	@TableField("is_wcdj")
	private Integer isWcdj;
	/**
	 * 是否验收确认：0-否、1-是
	 */
	@ApiModelProperty(value = "是否验收确认：0-否、1-是")
	@TableField("is_ys_confirm")
	private Integer isYsConfirm;
	/**
	 * 验收申请人
	 */
	@ApiModelProperty(value = "验收申请人")
	@TableField("ys_user_name")
	private String ysUserName;
	/**
	 * 验收申请人用户名
	 */
	@ApiModelProperty(value = "验收申请人用户名")
	@TableField("ys_user_code")
	private String ysUserCode;
	/**
	 * 验收申请人电话
	 */
	@ApiModelProperty(value = "验收申请人电话")
	@TableField("ys_user_phone")
	private String ysUserPhone;
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
