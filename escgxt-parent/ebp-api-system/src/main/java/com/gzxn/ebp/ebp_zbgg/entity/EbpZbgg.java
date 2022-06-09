package com.gzxn.ebp.ebp_zbgg.entity;

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
 * @Package: com.gzxn.ebp.ebp_zbgg.entity
 * @ClassName: EbpZbgg
 * @Description: 招标公告表-实体类
 * @author: CodeBird
 * @date:  2022-03-26 14:02:28 
 */
@ApiModel(value = "招标公告表")
@Data
public class EbpZbgg extends BaseEntity<EbpZbgg> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	@TableId("id")
	private String id;
	/**
	 * 发布人
	 */
	@ApiModelProperty(value = "发布人")
	@TableField("fbr")
	private String fbr;
	/**
	 * 发布时间
	 */
	@ApiModelProperty(value = "发布时间")
	@TableField("fbrq")
	private Date fbrq;
	/**
	 * 标题
	 */
	@ApiModelProperty(value = "标题")
	@TableField("title")
	private String title;
	/**
	 * 招标项目id
	 */
	@ApiModelProperty(value = "招标项目id")
	@TableField("zbxm_id")
	private String zbxmId;
	/**
	 * 采购包id
	 */
	@ApiModelProperty(value = "采购包id")
	@TableField("cgbid")
	private String cgbid;
	/**
	 * 项目编号
	 */
	@ApiModelProperty(value = "项目编号")
	@TableField("xmbh")
	private String xmbh;
	/**
	 * 项目名称
	 */
	@ApiModelProperty(value = "项目名称")
	@TableField("xmmc")
	private String xmmc;
	/**
	 * 开标与评标地点
	 */
	@ApiModelProperty(value = "开标与评标地点")
	@TableField("kpbdd")
	private String kpbdd;
	/**
	 * 招标公告地址栏
	 */
	@ApiModelProperty(value = "招标公告地址栏")
	@TableField("zbdzl")
	private String zbdzl;
	/**
	 * 开标日期
	 */
	@ApiModelProperty(value = "开标日期")
	@TableField("kbrq")
	private Date kbrq;
	/**
	 * 报名开始时间
	 */
	@ApiModelProperty(value = "报名开始时间")
	@TableField("bmkssj")
	private Date bmkssj;
	/**
	 * 报名结束时间
	 */
	@ApiModelProperty(value = "报名结束时间")
	@TableField("bmjssj")
	private Date bmjssj;
	/**
	 * 是否已发布：0-否、1-是
	 */
	@ApiModelProperty(value = "是否已发布：0-否、1-是")
	@TableField("is_fb")
	private Integer isFb;
	/**
	 * 是否更正：0-否、1-是
	 */
	@ApiModelProperty(value = "是否更正：0-否、1-是")
	@TableField("is_gz")
	private Integer isGz;
	/**
	 * 是否变更：0-否、1-是
	 */
	@ApiModelProperty(value = "是否变更：0-否、1-是")
	@TableField("is_bg")
	private Integer isBg;
	/**
	 * 是否暂停公告：0-否、1-是
	 */
	@ApiModelProperty(value = "是否暂停公告：0-否、1-是")
	@TableField("is_stop")
	private Integer isStop;
	/**
	 * 是否完成变更：0-否、1-是
	 */
	@ApiModelProperty(value = "是否完成变更：0-否、1-是")
	@TableField("is_wcbg")
	private Integer isWcbg;
	/**
	 * 是否提交登记：0-否、1-是
	 */
	@ApiModelProperty(value = "是否提交登记：0-否、1-是")
	@TableField("is_submit")
	private Integer isSubmit;
	/**
	 * 是否完成提交登记：0-否、1-是
	 */
	@ApiModelProperty(value = "是否完成提交登记：0-否、1-是")
	@TableField("is_wcdj")
	private Integer isWcdj;
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
