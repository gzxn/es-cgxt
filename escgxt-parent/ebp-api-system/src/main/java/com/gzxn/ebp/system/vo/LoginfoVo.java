package com.gzxn.ebp.system.vo;

import com.gzxn.ebp.system.entity.Loginfo;

/**
 * @Package: com.gzxn.ebp.system.vo
 * @ClassName: LoginfoVo
 * @Author: CodeBird
 * @Date: 2022-03-06 18:05
 * @Description: 日志信息Vo
 */
public class LoginfoVo extends Loginfo {
    private String uname;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

}
