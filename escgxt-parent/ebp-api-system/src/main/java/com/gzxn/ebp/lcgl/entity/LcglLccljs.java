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
 * @ClassName: LcglLccljs
 * @Description: 流程处理角色表-实体类
 * @author: CodeBird
 * @date:  2022-03-26 14:03:56 
 */
@ApiModel(value = "流程处理角色表")
@Data
public class LcglLccljs extends BaseEntity<LcglLccljs> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	@TableId("id")
	private String id;
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
	 * 处理角色代码
	 */
	@ApiModelProperty(value = "处理角色代码")
	@TableField("cljsdm")
	private String cljsdm;
	/**
	 * 处理角色
	 */
	@ApiModelProperty(value = "处理角色")
	@TableField("cljs")
	private String cljs;
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
