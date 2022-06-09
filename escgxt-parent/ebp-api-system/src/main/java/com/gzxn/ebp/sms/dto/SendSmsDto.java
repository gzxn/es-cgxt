package com.gzxn.ebp.sms.dto;

import com.gzxn.ebp.sms.entity.Sms;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Package: com.gzxn.ebp.sms.dto
 * @ClassName: SendSmsDto
 * @Author: CodeBird
 * @Date: 2022-03-06 19:14
 * @Description: 发送信息Dto
 */
@ApiModel(value = "发送信息Dto")
@Data
public class SendSmsDto extends Sms {

    /**
     * 发送类型 1-广播、2-个人
     */
    @ApiModelProperty(value = "发送类型 1-广播、2-个人", required = true)
    private String stype;
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
}