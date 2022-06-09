package com.gzxn.ebp.ebp_ysd.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gzxn.core.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @Package: com.gzxn.ebp.ebp_ysd.entity
 * @ClassName: EbpYsd
 * @Description: 验收单表-实体类
 * @author: CodeBird
 * @date:  2022-03-26 14:03:06 
 */
@ApiModel(value = "验收单表")
@Data
public class EbpYsd extends BaseEntity<EbpYsd> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	@TableId("id")
	private String id;
	/**
	 * 招标结果id
	 */
	@ApiModelProperty(value = "招标结果id", required = true)
	@TableField("zbjg_id")
	private String zbjgId;
	/**
	 * 合同id
	 */
	@ApiModelProperty(value = "合同id")
	@TableField("htid")
	private String htid;
	/**
	 * 到货时间
	 */
	@ApiModelProperty(value = "到货时间")
	@TableField("dhsj")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JSONField(format = "yyyy-MM-dd")
	private Date dhsj;
	/**
	 * 是否验收：0-否、1-是
	 */
	@ApiModelProperty(value = "是否验收：0-否、1-是")
	@TableField("is_success")
	private String isSuccess;
	/**
	 * 验收状态
	 */
	@ApiModelProperty(value = "验收状态")
	@TableField("yszt")
	private String yszt;
	/**
	 * 验收申请附件
	 */
	@ApiModelProperty(value = "验收申请附件")
	@TableField("yssqfj")
	private String yssqfj;
	/**
	 * 正式验收报告
	 */
	@ApiModelProperty(value = "正式验收报告")
	@TableField("zsysbg")
	private String zsysbg;
	/**
	 * 货物类型
	 */
	@ApiModelProperty(value = "货物类型")
	@TableField("hwlx")
	private String hwlx;
	/**
	 * 当前节点
	 */
	@ApiModelProperty(value = "当前节点")
	@TableField("dqjd")
	private String dqjd;
	/**
	 * 历史处理人用户名
	 */
	@ApiModelProperty(value = "历史处理人用户名")
	@TableField("lsclryhm")
	private String lsclryhm;
	/**
	 * 当前处理人
	 */
	@ApiModelProperty(value = "当前处理人")
	@TableField("dqclr")
	private String dqclr;
	/**
	 * 当前处理人用户名
	 */
	@ApiModelProperty(value = "当前处理人用户名")
	@TableField("dqclryhm")
	private String dqclryhm;
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
