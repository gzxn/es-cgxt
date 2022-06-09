package com.gzxn.ebp.lcgl.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gzxn.core.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Copyright  2022-03-26 QLH. Tech Ltd. All rights reserved.
 * 
 * @Package: com.gzxn.ebp.lcgl.entity
 * @ClassName: LcglRwdbb
 * @Description: 流程任务待办表-实体类
 * @author: CodeBird
 * @date:  2022-03-26 14:03:56 
 */
@ApiModel(value = "流程任务待办表")
@Data
public class LcglRwdbb extends BaseEntity<LcglRwdbb> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	@TableId("id")
	private String id;
	/**
	 * 是否会签：0-否、1-是
	 */
	@ApiModelProperty(value = "是否会签：0-否、1-是")
	@TableField("sfhq")
	private Integer sfhq;
	/**
	 * 流程名称
	 */
	@ApiModelProperty(value = "流程名称")
	@TableField("lcmc")
	private String lcmc;
	/**
	 * 主题
	 */
	@ApiModelProperty(value = "主题")
	@TableField("zt")
	private String zt;
	/**
	 * 当前处理人
	 */
	@ApiModelProperty(value = "当前处理人")
	@TableField("dbr")
	private String dbr;
	/**
	 * 发起人
	 */
	@ApiModelProperty(value = "发起人")
	@TableField("sqr")
	private String sqr;
	/**
	 * 当前步骤
	 */
	@ApiModelProperty(value = "当前步骤")
	@TableField("jdmc")
	private String jdmc;
	/**
	 * 生成时间
	 */
	@ApiModelProperty(value = "生成时间")
	@TableField("scsj")
	private Date scsj;
	/**
	 * 是否并行
	 */
	@ApiModelProperty(value = "是否并行")
	@TableField("sfbx")
	private String sfbx;
	/**
	 * 待办人用户名
	 */
	@ApiModelProperty(value = "待办人用户名")
	@TableField("dbryhm")
	private String dbryhm;
	/**
	 * 流程id
	 */
	@ApiModelProperty(value = "流程id")
	@TableField("lcid")
	private String lcid;
	/**
	 * 流程实例id
	 */
	@ApiModelProperty(value = "流程实例id")
	@TableField("lcslid")
	private String lcslid;
	/**
	 * 待办状态
	 */
	@ApiModelProperty(value = "待办状态")
	@TableField("dbzt")
	private String dbzt;
	/**
	 * 流程状态
	 */
	@ApiModelProperty(value = "流程状态")
	@TableField("lczt")
	private String lczt;
	/**
	 * 数据表单id
	 */
	@ApiModelProperty(value = "数据表单id")
	@TableField("sjbdid")
	private String sjbdid;
	/**
	 * 节点id
	 */
	@ApiModelProperty(value = "节点id")
	@TableField("jdid")
	private String jdid;
	/**
	 * 申请人用户名
	 */
	@ApiModelProperty(value = "申请人用户名")
	@TableField("sqryhm")
	private String sqryhm;
	/**
	 * 是否是代审核人：0-否、1-是
	 */
	@ApiModelProperty(value = "是否是代审核人：0-否、1-是")
	@TableField("is_replace")
	private Integer isReplace;
	/**
	 * 是否发送消息：0-否、1-是
	 */
	@ApiModelProperty(value = "是否发送消息：0-否、1-是")
	@TableField("is_sendmsg")
	private Integer isSendmsg;
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
