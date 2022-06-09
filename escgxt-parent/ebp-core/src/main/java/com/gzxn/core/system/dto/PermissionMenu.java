package com.gzxn.core.system.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Package: com.gzxn.core.system.dto
 * @ClassName: PermissionMenu
 * @Author: CodeBird
 * @Date: 2022-03-06 11:41
 * @Description: 权限菜单
 */
@Data
public class PermissionMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    private String id;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", required = true)
    private String name;
    /**
     * 1-展示菜单、2-功能菜单、3功能按钮
     */
    @ApiModelProperty(value = "1-展示菜单、2-功能菜单、3功能按钮", required = true)
    private Integer type;
    /**
     * 父id
     */
    @ApiModelProperty(value = "父id")
    private String pid;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", required = true)
    private Integer sort;
    /**
     * 等级
     */
    @ApiModelProperty(value = "等级", required = true)
    @TableField("level")
    private Integer level;
    /**
     * 权限编码
     */
    @ApiModelProperty(value = "权限编码", required = true)
    private String code;
    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String icon;
    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    @TableField("url")
    private String url;
    /**
     * 组件
     */
    @ApiModelProperty(value = "组件")
    private String component;
    /**
     * 打开方式：1-内部、2-外部
     */
    @ApiModelProperty(value = "打开方式：1-内部、2-外部", required = true)
    private Integer opentype;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;

}
