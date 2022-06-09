package com.gzxn.ebp.lcgl.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gzxn.core.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Copyright  2022-03-26 QLH. Tech Ltd. All rights reserved.
 * 
 * @Package: com.gzxn.ebp.lcgl.entity
 * @ClassName: LcglLcslb
 * @Description: 流程实例表-实体类
 * @author: CodeBird
 * @date:  2022-03-26 14:03:56 
 */
@ApiModel(value = "流程实例表")
@Data
public class LcglLcslb extends BaseEntity<LcglLcslb> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	@TableId("id")
	private String id;
	/**
	 * 主题
	 */
	@ApiModelProperty(value = "主题")
	@TableField("zt")
	private String zt;
	/**
	 * 详情id
	 */
	@ApiModelProperty(value = "详情id")
	@TableField("xqid")
	private String xqid;
	/**
	 * 当前处理人用户名
	 */
	@ApiModelProperty(value = "当前处理人用户名")
	@TableField("dqclryhm")
	private String dqclryhm;
	/**
	 * 当前处理人
	 */
	@ApiModelProperty(value = "当前处理人")
	@TableField("dqclr")
	private String dqclr;
	/**
	 * 申请人用户名
	 */
	@ApiModelProperty(value = "申请人用户名")
	@TableField("sqryhm")
	private String sqryhm;
	/**
	 * 申请人
	 */
	@ApiModelProperty(value = "申请人")
	@TableField("sqr")
	private String sqr;
	/**
	 * 流程id
	 */
	@ApiModelProperty(value = "流程id")
	@TableField("lcid")
	private String lcid;
	/**
	 * 流程名称
	 */
	@ApiModelProperty(value = "流程名称")
	@TableField("lcmc")
	private String lcmc;
	/**
	 * 数据表单主键
	 */
	@ApiModelProperty(value = "数据表单主键")
	@TableField("sjbdzj")
	private String sjbdzj;
	/**
	 * 元数据id
	 */
	@ApiModelProperty(value = "元数据id")
	@TableField("ysjid")
	private String ysjid;
	/**
	 * 所属表名
	 */
	@ApiModelProperty(value = "所属表名")
	@TableField("ssbm")
	private String ssbm;
	/**
	 * 当前任务id
	 */
	@ApiModelProperty(value = "当前任务id")
	@TableField("dqrwid")
	private String dqrwid;
	/**
	 * 当前任务
	 */
	@ApiModelProperty(value = "当前任务")
	@TableField("dqrw")
	private String dqrw;
	/**
	 * 历史处理人
	 */
	@ApiModelProperty(value = "历史处理人")
	@TableField("lsclr")
	private String lsclr;
	/**
	 * 流程状态
	 */
	@ApiModelProperty(value = "流程状态")
	@TableField("lczt")
	private String lczt;
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
