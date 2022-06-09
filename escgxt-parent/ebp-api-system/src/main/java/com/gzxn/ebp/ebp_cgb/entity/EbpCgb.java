package com.gzxn.ebp.ebp_cgb.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gzxn.core.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Copyright  2022-03-26 QLH. Tech Ltd. All rights reserved.
 *
 * @Package: com.gzxn.ebp.ebp_cgb.entity
 * @ClassName: EbpCgb
 * @Description: 采购包表-实体类
 * @author: CodeBird
 * @date: 2022-03-26 13:59:18
 */
@ApiModel(value = "采购包表")
@Data
public class EbpCgb extends BaseEntity<EbpCgb> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    @TableId("id")
    private String id;
    /**
     * 采购包编号
     */
    @ApiModelProperty(value = "采购包编号", required = true)
    @TableField("cgbbh")
    private String cgbbh;
    /**
     * 采购包名称
     */
    @ApiModelProperty(value = "采购包名称", required = true)
    @TableField("cgbmc")
    private String cgbmc;
    /**
     * 采购包名称
     */
    @ApiModelProperty(value = "采购任务id")
    @TableField("cgrwid")
    private String cgrwid;
    /**
     * 组织形式
     */
    @ApiModelProperty(value = "组织形式")
    @TableField("zzxs")
    private String zzxs;
    /**
     * 采购方式
     */
    @ApiModelProperty(value = "采购方式")
    @TableField("cgfs")
    private String cgfs;
    /**
     * 预算金额(元)
     */
    @ApiModelProperty(value = "预算金额(元)")
    @TableField("ysje")
    private BigDecimal ysje;
    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人")
    @TableField("lxr")
    private String lxr;
    /**
     * 申报日期
     */
    @ApiModelProperty(value = "申报日期")
    @TableField("sbrq")
    private Date sbrq;
    /**
     * 采购执行人用户名
     */
    @ApiModelProperty(value = "采购执行人用户名")
    @TableField("cgzxryhm")
    private String cgzxryhm;
    /**
     * 采购执行人
     */
    @ApiModelProperty(value = "采购执行人")
    @TableField("cgzxr")
    private String cgzxr;
    /**
     * 代理机构抽取方式
     */
    @ApiModelProperty(value = "代理机构抽取方式")
    @TableField("cqfs")
    private String cqfs;
    /**
     * 项目类型
     */
    @ApiModelProperty(value = "项目类型")
    @TableField("xmlx")
    private String xmlx;
    /**
     * 流程状态
     */
    @ApiModelProperty(value = "流程状态")
    @TableField("bpm_status")
    private String bpmStatus;
    /**
     * 代理机构编号
     */
    @ApiModelProperty(value = "代理机构编号")
    @TableField("dljgbh")
    private String dljgbh;
    /**
     * 代理机构id
     */
    @ApiModelProperty(value = "代理机构id")
    @TableField("dljgid")
    private String dljgid;
    /**
     * 是否走代理流程：0-否、1-是
     */
    @ApiModelProperty(value = "是否走代理流程：0-否、1-是")
    @TableField("is_proxy")
    private Integer isProxy;
    /**
     * 是否委托：0-否、1-是
     */
    @ApiModelProperty(value = "是否委托：0-否、1-是")
    @TableField("is_wt")
    private Integer isWt;
    /**
     * 是否已推送：0-否、1-是
     */
    @ApiModelProperty(value = "是否已推送：0-否、1-是")
    @TableField("is_ts")
    private Integer isTs;
    /**
     * 是否生成至招标项目：0-否、1-是
     */
    @ApiModelProperty(value = "是否生成至招标项目：0-否、1-是")
    @TableField("is_zb")
    private Integer isZb;
    /**
     * 是否登记结果：0-否、1-是
     */
    @ApiModelProperty(value = "是否登记结果：0-否、1-是")
    @TableField("is_djjg")
    private Integer isDjjg;
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
