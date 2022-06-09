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
 * @ClassName: LcglJdlxb
 * @Description: 流程节点历史表-实体类
 * @author: CodeBird
 * @date:  2022-03-26 14:03:56 
 */
@ApiModel(value = "流程节点历史表")
@Data
public class LcglJdlxb extends BaseEntity<LcglJdlxb> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	@TableId("id")
	private String id;
	/**
	 * 顺序号
	 */
	@ApiModelProperty(value = "顺序号")
	@TableField("sxh")
	private Integer sxh;
	/**
	 * 流程id
	 */
	@ApiModelProperty(value = "流程id")
	@TableField("lcid")
	private String lcid;
	/**
	 * 起始节点主键
	 */
	@ApiModelProperty(value = "起始节点主键")
	@TableField("qsjdid")
	private String qsjdid;
	/**
	 * 指向节点主键
	 */
	@ApiModelProperty(value = "指向节点主键")
	@TableField("zxjdid")
	private String zxjdid;
	/**
	 * 流程实例id
	 */
	@ApiModelProperty(value = "流程实例id")
	@TableField("lcslid")
	private String lcslid;
	/**
	 * 表单id
	 */
	@ApiModelProperty(value = "表单id")
	@TableField("bdid")
	private String bdid;
	/**
	 * 始节点是否会签：0-否、1-是
	 */
	@ApiModelProperty(value = "始节点是否会签：0-否、1-是")
	@TableField("qjdsfhq")
	private Integer qjdsfhq;
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
