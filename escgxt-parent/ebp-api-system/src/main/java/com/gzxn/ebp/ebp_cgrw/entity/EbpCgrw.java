package com.gzxn.ebp.ebp_cgrw.entity;

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
 * @Package: com.gzxn.ebp.ebp_cgrw.entity
 * @ClassName: EbpCgrw
 * @Description: 采购任务表-实体类
 * @author: CodeBird
 * @date: 2022-03-26 14:01:01
 */
@ApiModel(value = "采购任务表")
@Data
public class EbpCgrw extends BaseEntity<EbpCgrw> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    @TableId("id")
    private String id;
    /**
     * 采购任务编号
     */
    @ApiModelProperty(value = "采购任务编号", required = true)
    @TableField("cgrwbh")
    private String cgrwbh;
    /**
     * 采购任务名称
     */
    @ApiModelProperty(value = "采购任务名称", required = true)
    @TableField("cgrwmc")
    private String cgrwmc;
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
     * 采购申请id
     */
    @ApiModelProperty(value = "采购申请id")
    @TableField("cgsqid")
    private String cgsqid;
    /**
     * 代理机构id
     */
    @ApiModelProperty(value = "代理机构id")
    @TableField("dljgid")
    private String dljgid;
    /**
     * 代理机构编号
     */
    @ApiModelProperty(value = "代理机构编号")
    @TableField("dljgbh")
    private String dljgbh;
    /**
     * 代理机构名称
     */
    @ApiModelProperty(value = "代理机构名称")
    @TableField("dljgmc")
    private String dljgmc;
    /**
     * 执行部门
     */
    @ApiModelProperty(value = "执行部门")
    @TableField("zxbm")
    private String zxbm;
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
     * 申报日期
     */
    @ApiModelProperty(value = "申报日期")
    @TableField("sbrq")
    private Date sbrq;
    /**
     * 是否登记结果
     */
    @ApiModelProperty(value = "是否登记结果")
    @TableField("is_djjg")
    private String isDjjg;
    /**
     * 抽取方式
     */
    @ApiModelProperty(value = "抽取方式")
    @TableField("cqfs")
    private String cqfs;
    /**
     * 项目类型
     */
    @ApiModelProperty(value = "项目类型")
    @TableField("xmlx")
    private String xmlx;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("bz")
    private String bz;
    /**
     * 流程状态
     */
    @ApiModelProperty(value = "流程状态")
    @TableField("bpm_status")
    private String bpmStatus;
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
