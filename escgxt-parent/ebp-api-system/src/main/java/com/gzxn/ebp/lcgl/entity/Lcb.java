package com.gzxn.ebp.lcgl.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gzxn.core.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @Package: com.gzxn.ebp.lcgl.entity
 * @ClassName: Lcb
 * @Description: -实体类
 * @author: CodeBird
 * @date:  2022-05-02 11:53:24 
 */
@ApiModel(value = "")
@Data
public class Lcb extends BaseEntity<Lcb> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	@TableId("id")
	private String id;
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
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id")
	@TableField("uid")
	private String uid;
	/**
	 * 表单id
	 */
	@ApiModelProperty(value = "表单id")
	@TableField("bdid")
	private String bdid;
	/**
	 * 表单名称
	 */
	@ApiModelProperty(value = "表单名称")
	@TableField("bdname")
	private String bdname;
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
	 * 审核结果
	 */
	@ApiModelProperty(value = "审核结果")
	@TableField("shjg")
	private Integer shjg;
	/**
	 * 审核意见
	 */
	@ApiModelProperty(value = "审核意见")
	@TableField("shyj")
	private String shyj;
	/**
	 * 顺序号
	 */
	@ApiModelProperty(value = "顺序号")
	@TableField("sxh")
	private String sxh;
	 
	 
	 
	@Override
	protected Serializable pkVal() {
				return id;
	}
 

	 
}
