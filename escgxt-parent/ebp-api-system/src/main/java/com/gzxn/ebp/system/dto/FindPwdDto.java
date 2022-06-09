package com.gzxn.ebp.system.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @Package: com.gzxn.ebp.system.dto
 * @ClassName: FindPwdDto
 * @Author: CodeBird
 * @Date: 2022-03-06 17:55
 * @Description: 找密码Dto
 */
@ApiModel(value = "找密码Dto")
@Data
public class FindPwdDto implements Serializable {
    /**
     * 邮件
     */
    private String email;
    /**
     * 验证码
     */
    private String code;
    /**
     * 新密码
     */
    private String newPwd;
}

