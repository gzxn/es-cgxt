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
 * @ClassName: LcglLcb
 * @Description: 流程表-实体类
 * @author: CodeBird
 * @date:  2022-03-26 14:03:56 
 */
@ApiModel(value = "流程表")
@Data
public class LcglLcb extends BaseEntity<LcglLcb> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	@TableId("id")
	private String id;
	/**
	 * 详情URL
	 */
	@ApiModelProperty(value = "详情URL")
	@TableField("xqurl")
	private String xqurl;
	/**
	 * 流程名称
	 */
	@ApiModelProperty(value = "流程名称")
	@TableField("lcmc")
	private String lcmc;
	/**
	 * 描述
	 */
	@ApiModelProperty(value = "描述")
	@TableField("ms")
	private String ms;
	/**
	 * 元数据主键
	 */
	@ApiModelProperty(value = "元数据主键")
	@TableField("ysjzj")
	private String ysjzj;
	/**
	 * 所属表名
	 */
	@ApiModelProperty(value = "所属表名")
	@TableField("ssbm")
	private String ssbm;
	/**
	 * 是否手写签名：0-否、1-是
	 */
	@ApiModelProperty(value = "是否手写签名：0-否、1-是")
	@TableField("sfsxqm")
	private Integer sfsxqm;
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
