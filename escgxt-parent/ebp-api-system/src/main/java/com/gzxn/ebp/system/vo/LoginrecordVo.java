package com.gzxn.ebp.system.vo;

import com.gzxn.ebp.system.entity.Loginrecord;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Package: com.gzxn.ebp.system.vo
 * @ClassName: LoginrecordVo
 * @Author: CodeBird
 * @Date: 2022-03-06 18:05
 * @Description: 登录记录Vo
 */
public class LoginrecordVo extends Loginrecord {
    @ApiModelProperty(value = "用户名")
    private String uname;
    @ApiModelProperty(value = "手机")
    private String mobile;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
