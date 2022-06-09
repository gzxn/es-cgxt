package com.gzxn.ebp.sms.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Package: com.gzxn.ebp.sms.dto
 * @ClassName: EmailSmsDto
 * @Author: CodeBird
 * @Date: 2022-03-06 19:13
 * @Description: 邮件信息Dto
 */
@ApiModel(value = "邮件信息Dto")
@Data
public class EmailSmsDto implements Serializable {
    /**
     * 接受者
     */
    @ApiModelProperty(value = "接受者", required = true)
    private String to;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题", required = true)
    private String subject;
    /**
     * 发送类型 1-内容发送、2-模版发送
     */
    @ApiModelProperty(value = "发送类型 1-内容发送、2-模版发送", required = true)
    private Integer sendType;
    /**
     * 模版标识
     */
    @ApiModelProperty(value = "模版标识")
    private String tcode;
    /**
     * 模版参数
     */
    @ApiModelProperty(value = "模版参数")
    private String parameter;

    /**
     * 邮件内容
     */
    @ApiModelProperty(value = "邮件内容", required = true)
    private String content;
}
