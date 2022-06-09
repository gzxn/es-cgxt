package com.gzxn.ebp.system.vo;

import com.gzxn.core.system.dto.EmailSetting;
import com.gzxn.core.system.dto.SystemSetting;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Package: com.gzxn.ebp.system.vo
 * @ClassName: SettingVo
 * @Author: CodeBird
 * @Date: 2022-03-06 18:06
 * @Description: 设置Vo
 */
@Data
@ApiModel(value = "设置Vo")
public class SettingVo {
    /**
     * 系统设置
     */
    @ApiModelProperty("系统设置")
    private SystemSetting systemSetting;
    /**
     * 邮件设置
     */
    @ApiModelProperty("邮件设置")
    private EmailSetting emailSetting;
}
