package com.gzxn.ebp.sms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gzxn.core.base.entity.BaseEntity;
import com.gzxn.core.system.annotation.OptionDictionary;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Package: com.gzxn.ebp.sms.entity
 * @ClassName: Templates
 * @Author: CodeBird
 * @Date: 2022-03-06 19:11
 * @Description: 信息模版-实体类
 */
@ApiModel(value = "信息模版")
@Data
public class Templates extends BaseEntity<Templates> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    @TableId("id")
    private String id;
    /**
     * 模版标识
     */
    @ApiModelProperty(value = "模版标识", required = true)
    @TableField("code")
    private String code;
    /**
     * 类型 1-站内信、2-邮件
     */
    @OptionDictionary(key = "template-type")
    @ApiModelProperty(value = "类型 1-站内信、2-邮件", required = true)
    @TableField("type")
    private Integer type;
    /**
     * 模版名称
     */
    @ApiModelProperty(value = "模版名称", required = true)
    @TableField("name")
    private String name;
    /**
     * 模版内容
     */
    @ApiModelProperty(value = "模版内容")
    @TableField("content")
    private String content;



    @Override
    protected Serializable pkVal() {
        return id;
    }



}
