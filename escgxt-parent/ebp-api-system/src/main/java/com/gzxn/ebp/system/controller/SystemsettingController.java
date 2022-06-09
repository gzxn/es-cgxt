package com.gzxn.ebp.system.controller;

import cn.hutool.core.bean.BeanUtil;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.system.annotation.LogModule;
import com.gzxn.core.system.annotation.LogOperation;
import com.gzxn.core.system.api.ISystemCommonApi;
import com.gzxn.core.system.dto.EmailSetting;
import com.gzxn.core.system.dto.SystemSetting;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.system.dto.SettingDto;
import com.gzxn.ebp.system.vo.SettingVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Package: com.gzxn.ebp.system.controller
 * @ClassName: SystemsettingController
 * @Author: CodeBird
 * @Date: 2022-03-06 19:25
 * @Description: 系统设置-控制器
 */
@Api(tags = "系统设置")
@LogModule(value = "系统设置")
@RestController
@RequestMapping("/system/systemsetting")
public class SystemsettingController extends BaseController {
    @Autowired
    private ISystemCommonApi iSystemCommonApi;

    @LogOperation("列表")
    @RequiresPermissions(value = { "00000030" })
    @ApiOperation(value = "列表")
    @GetMapping("/list")
    public ResponseBean<SettingVo> list() {
        SystemSetting systemSetting = iSystemCommonApi.getSystemSetting();
        EmailSetting emailSetting = iSystemCommonApi.getEmailSetting();
        SettingVo settingVo = new SettingVo();
        settingVo.setSystemSetting(systemSetting);
        settingVo.setEmailSetting(emailSetting);
        return ResponseBean.Success(settingVo);
    }

    @LogOperation("编辑")
    @RequiresPermissions(value = { "00000030" })
    @ApiOperation(value = "编辑")
    @PutMapping("/update")
    public ResponseBean update(@RequestBody SettingDto settingDto) {
        if (iSystemCommonApi.updateSystemSetting(BeanUtil.copyProperties(settingDto, SystemSetting.class)) & iSystemCommonApi.updateEmailSetting(BeanUtil.copyProperties(settingDto, EmailSetting.class))) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

}
