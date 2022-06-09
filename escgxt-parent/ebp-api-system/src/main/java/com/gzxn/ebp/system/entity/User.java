package com.gzxn.ebp.system.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gzxn.core.base.entity.BaseEntity;
import com.gzxn.core.system.annotation.OptionDictionary;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Package: com.gzxn.ebp.system.entity
 * @ClassName: User
 * @Author: CodeBird
 * @Date: 2022-03-06 17:53
 * @Description: 用户表-实体类
 */
@ApiModel(value = "用户表")
@Data
public class User extends BaseEntity<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    @TableId("id")
    private String id;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    @TableField("head")
    private String head;
    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名", required = true)
    @TableField("lname")
    private String lname;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @TableField("name")
    private String name;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true)
    @TableField("pwd")
    private String pwd;
    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    @TableField("birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date birth;
    /**
     * 性别 1-男、2-女
     */
    @OptionDictionary(key = "user-sex")
    @ApiModelProperty(value = "性别 1-男、2-女", required = true)
    @TableField("sex")
    private Integer sex;
    /**
     * 邮件
     */
    @ApiModelProperty(value = "邮件")
    @TableField("email")
    private String email;
    /**
     * 手机
     */
    @ApiModelProperty(value = "手机", required = true)
    @TableField("mobile")
    private String mobile;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("remarks")
    private String remarks;
    /**
     * 是否禁用：1-是、2-否
     */
    @ApiModelProperty(value = "是否禁用：1-是、2-否", required = true)
    @TableField("isdisable")
    private Integer isdisable;

}
