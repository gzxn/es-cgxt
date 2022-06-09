package com.gzxn.ebp.ebp_cgrw.entity;

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
 * @Package: com.gzxn.ebp.ebp_cgrw.entity
 * @ClassName: EbpCgrwQd
 * @Description: 采购任务清单表-实体类
 * @author: CodeBird
 * @date:  2022-03-26 14:01:01 
 */
@ApiModel(value = "采购任务清单表")
@Data
public class EbpCgrwQd extends BaseEntity<EbpCgrwQd> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	@TableId("id")
	private String id;
	/**
	 * 设备id
	 */
	@ApiModelProperty(value = "设备id")
	@TableField("sbid")
	private String sbid;
	/**
	 * 采购任务id
	 */
	@ApiModelProperty(value = "采购任务id")
	@TableField("cgrwid")
	private String cgrwid;
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
