package com.gzxn.core.system.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.gzxn.core.system.annotation.OptionDictionary;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Package: com.gzxn.core.system.dto
 * @ClassName: LoginUser
 * @Author: CodeBird
 * @Date: 2022-03-06 11:14
 * @Description: 用户登录
 */
@Data
public class LoginUser implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    private String id;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String head;
    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名", required = true)
    private String lname;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date birth;
    /**
     * 性别 1-男、2-女
     */
    @OptionDictionary(key = "user-sex")
    @ApiModelProperty(value = "性别 1-男、2-女", required = true)
    private Integer sex;
    /**
     * 邮件
     */
    @ApiModelProperty(value = "邮件")
    private String email;
    /**
     * 手机
     */
    @ApiModelProperty(value = "手机", required = true)
    private String mobile;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;
    /**
     * 是否禁用：1-是、2-否
     */
    @ApiModelProperty(value = "是否禁用：1-是、2-否", required = true)
    private Integer isdisable;

    /**
     * 部门标识包含子
     */
    @ApiModelProperty(value = "部门标识包含子")
    private Set<String> departmentCodes;
    /**
     * 角色标识包含子
     */
    @ApiModelProperty(value = "角色标识包含子")
    private Set<String> roleCodes;

    /**
     * 部门标识
     */
    @ApiModelProperty(value = "部门标识")
    private Set<String> departmentCode;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称包含子")
    private Set<String> departmentNames;

    /**
     * 角色标识
     */
    @ApiModelProperty(value = "角色标识")
    private Set<String> roleCode;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称包含子")
    private Set<String> roleNames;

    /**
     * 权限标识
     */
    @ApiModelProperty(value = "权限标识")
    private Set<String> permissionCode;

    /**
     * 权限菜单
     */
    @ApiModelProperty(value = "权限菜单")
    private List<PermissionMenu> permissionMenus;

}
