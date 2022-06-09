package com.gzxn.core.system.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Package: com.gzxn.core.system.dto
 * @ClassName: SystemSetting
 * @Author: CodeBird
 * @Date: 2022-03-06 12:03
 * @Description: 系统设置
 */
@Data
public class SystemSetting implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 系统标题
     */
    @ApiModelProperty(value = "系统标题")
    private String title;
    /**
     * 系统名称
     */
    @ApiModelProperty(value = "系统名称")
    private String systemName;
    /**
     * 系统图标
     */
    @ApiModelProperty(value = "系统图标")
    private String icon;
    /**
     * 系统备案
     */
    @ApiModelProperty(value = "系统备案")
    private String icp;
    /**
     * 底部内容
     */
    @ApiModelProperty(value = "底部内容")
    private String footer;
}
