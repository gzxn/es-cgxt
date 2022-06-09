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
 * @ClassName: LcglLzlsb
 * @Description: 流程流转历史表-实体类
 * @author: CodeBird
 * @date:  2022-03-26 14:03:56 
 */
@ApiModel(value = "流程流转历史表")
@Data
public class LcglLzlsb extends BaseEntity<LcglLzlsb> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	@TableId("id")
	private String id;
	/**
	 * 任务表id
	 */
	@ApiModelProperty(value = "任务表id")
	@TableField("rwid")
	private String rwid;
	/**
	 * 生成时间
	 */
	@ApiModelProperty(value = "生成时间")
	@TableField("scsj")
	private Date scsj;
	/**
	 * 处理时间
	 */
	@ApiModelProperty(value = "处理时间")
	@TableField("clsj")
	private Date clsj;
	/**
	 * 审核意见
	 */
	@ApiModelProperty(value = "审核意见")
	@TableField("shyj")
	private String shyj;
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
	 * 节点名称
	 */
	@ApiModelProperty(value = "节点名称")
	@TableField("jdmc")
	private String jdmc;
	/**
	 * 处理人用户名
	 */
	@ApiModelProperty(value = "处理人用户名")
	@TableField("clryhm")
	private String clryhm;
	/**
	 * 处理人
	 */
	@ApiModelProperty(value = "处理人")
	@TableField("clr")
	private String clr;
	/**
	 * 顺序号
	 */
	@ApiModelProperty(value = "顺序号")
	@TableField("sxh")
	private Integer sxh;
	/**
	 * 操作类型
	 */
	@ApiModelProperty(value = "操作类型")
	@TableField("czlx")
	private String czlx;
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
