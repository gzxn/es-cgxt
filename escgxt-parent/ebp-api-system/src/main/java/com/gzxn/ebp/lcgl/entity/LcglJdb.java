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
 * @ClassName: LcglJdb
 * @Description: 流程节点表-实体类
 * @author: CodeBird
 * @date:  2022-03-26 14:03:56 
 */
@ApiModel(value = "流程节点表")
@Data
public class LcglJdb extends BaseEntity<LcglJdb> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	@TableId("id")
	private String id;
	/**
	 * 建议处理时间
	 */
	@ApiModelProperty(value = "建议处理时间")
	@TableField("jyclsj")
	private Integer jyclsj;
	/**
	 * 并行是否通过
	 */
	@ApiModelProperty(value = "并行是否通过")
	@TableField("bxsftg")
	private Integer bxsftg;
	/**
	 * 待理人监听
	 */
	@ApiModelProperty(value = "待理人监听")
	@TableField("dlrjt")
	private String dlrjt;
	/**
	 * 是否当前任务节点
	 */
	@ApiModelProperty(value = "是否当前任务节点")
	@TableField("dqrw")
	private String dqrw;
	/**
	 * 处理人
	 */
	@ApiModelProperty(value = "处理人")
	@TableField("clr")
	private String clr;
	/**
	 * 处理人用户名
	 */
	@ApiModelProperty(value = "处理人用户名")
	@TableField("clryhm")
	private String clryhm;
	/**
	 * 是否开始结束节点
	 */
	@ApiModelProperty(value = "是否开始结束节点")
	@TableField("sfksjsjd")
	private Integer sfksjsjd;
	/**
	 * 处理方式
	 */
	@ApiModelProperty(value = "处理方式")
	@TableField("clfs")
	private String clfs;
	/**
	 * 所属表名
	 */
	@ApiModelProperty(value = "所属表名")
	@TableField("ssbm")
	private String ssbm;
	/**
	 * 节点名称
	 */
	@ApiModelProperty(value = "节点名称")
	@TableField("jdmc")
	private String jdmc;
	/**
	 * 流程id
	 */
	@ApiModelProperty(value = "流程id")
	@TableField("lcid")
	private String lcid;
	/**
	 * 表单主键
	 */
	@ApiModelProperty(value = "表单主键")
	@TableField("bdzj")
	private String bdzj;
	/**
	 * 流程实例id
	 */
	@ApiModelProperty(value = "流程实例id")
	@TableField("lcslid")
	private String lcslid;
	/**
	 * 顺序号
	 */
	@ApiModelProperty(value = "顺序号")
	@TableField("sxh")
	private Integer sxh;
	/**
	 * 是否会签
	 */
	@ApiModelProperty(value = "是否会签")
	@TableField("sfhq")
	private Integer sfhq;
	/**
	 * 处理节点监听
	 */
	@ApiModelProperty(value = "处理节点监听")
	@TableField("zxjdjt")
	private String zxjdjt;
	/**
	 * 跳过已审核人
	 */
	@ApiModelProperty(value = "跳过已审核人")
	@TableField("tgyshr")
	private Integer tgyshr;
	/**
	 * 条件表达式
	 */
	@ApiModelProperty(value = "条件表达式")
	@TableField("tjbds")
	private String tjbds;
	/**
	 * 节点编码
	 */
	@ApiModelProperty(value = "节点编码")
	@TableField("jdbm")
	private String jdbm;
	/**
	 * 必过节点：0-否、1-是
	 */
	@ApiModelProperty(value = "必过节点：0-否、1-是")
	@TableField("sfbgjd")
	private Integer sfbgjd;
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
