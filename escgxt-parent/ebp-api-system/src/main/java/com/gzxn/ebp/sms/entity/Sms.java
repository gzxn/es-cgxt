package com.gzxn.ebp.sms.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.gzxn.core.base.entity.BaseEntity;
import com.gzxn.core.system.annotation.OptionDictionary;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Package: com.gzxn.ebp.sms.entity
 * @ClassName: Sms
 * @Author: CodeBird
 * @Date: 2022-03-06 19:10
 * @Description: 站内信息表-实体类
 */
@ApiModel(value = "站内信息表")
@Data
public class Sms extends BaseEntity<Sms> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id", required = true)
    @TableField("id")
    private String id;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题", required = true)
    @TableField("title")
    private String title;
    /**
     * 发送者id
     */
    @ApiModelProperty(value = "发送者id", required = true)
    @TableField("sid")
    private String sid;
    /**
     * 接收者id
     */
    @ApiModelProperty(value = "接收者id", required = true)
    @TableField("rid")
    private String rid;
    /**
     * 阅读状态:1-未读、2-已读
     */
    @ApiModelProperty(value = "阅读状态:1-未读、2-已读", required = true)
    @TableField("rstate")
    private Integer rstate;
    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    @TableField("content")
    private String content;
    /**
     * 内容级别：1-普通 2-一般 3-紧急
     */
    @OptionDictionary(key="sms-level")
    @ApiModelProperty(value = "内容级别：1-普通 2-一般 3-紧急", required = true)
    @TableField("level")
    private Integer level;
    /**
     * 内容类型：1-消息、2-通知、3-公告、4-其他
     */
    @OptionDictionary(key="sms-type")
    @ApiModelProperty(value = "内容类型：1-消息、2-通知、3-公告、4-其他", required = true)
    @TableField("type")
    private Integer type;
    /**
     * 发送时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "发送时间", required = true)
    @TableField("stime")
    private Date stime;
    /**
     * 阅读时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "阅读时间")
    @TableField("rtime")
    private Date rtime;
    /**
     * 文件
     */
    @ApiModelProperty(value = "文件")
    @TableField("file")
    private String file;
    /**
     * url
     */
    @ApiModelProperty(value = "url")
    @TableField("url")
    private String url;


}
