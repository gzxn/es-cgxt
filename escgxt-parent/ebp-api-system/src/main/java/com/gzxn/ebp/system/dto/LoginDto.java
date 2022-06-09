package com.gzxn.ebp.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Package: com.gzxn.ebp.system.dto
 * @ClassName: LoginDto
 * @Author: CodeBird
 * @Date: 2022-03-06 17:56
 * @Description: 登录对象
 */
@ApiModel(value = "登录对象")
@Data
public class LoginDto implements Serializable {
    @ApiModelProperty(value = "登录名", required = true)
    private String name;
    @ApiModelProperty(value = "密码", required = true)
    private String pwd;
    @ApiModelProperty(value = "验证码", required = true)
    private String code;
    @ApiModelProperty(value = "验证码key", required = true)
    private String key;

}