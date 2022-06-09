package com.gzxn.core.system.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Package: com.gzxn.core.system.dto
 * @ClassName: EmailSetting
 * @Author: CodeBird
 * @Date: 2022-03-06 11:38
 * @Description: 邮件设置
 */
@Data
public class EmailSetting implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 邮件发送者
     */
    @ApiModelProperty(value = "邮件发送者")
    private String mailSender;
    /**
     * 邮件服务器
     */
    @ApiModelProperty(value = "邮件服务器")
    private String host;
    /**
     * 邮件服务器端口
     */
    @ApiModelProperty(value = "邮件服务器端口")
    private Integer port;
    /**
     * 邮件服务用户
     */
    @ApiModelProperty(value = "邮件服务用户")
    private String user;
    /**
     * 邮件服务授权码
     */
    @ApiModelProperty(value = "邮件服务授权码")
    private String pass;

}
