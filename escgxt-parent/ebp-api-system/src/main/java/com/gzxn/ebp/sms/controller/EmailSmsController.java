package com.gzxn.ebp.sms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.system.annotation.LogModule;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.sms.dto.EmailSmsDto;
import com.gzxn.ebp.sms.entity.Templates;
import com.gzxn.ebp.sms.service.IEmailService;
import com.gzxn.ebp.sms.service.ITemplatesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Package: com.gzxn.ebp.sms.controller
 * @ClassName: EmailSmsController
 * @Author: CodeBird
 * @Date: 2022-03-06 19:17
 * @Description: 邮件信息-控制器
 */
@Api(tags = "邮件信息")
@LogModule(value = "邮件信息")
@RestController
@RequestMapping("/sms/emailSms")
public class EmailSmsController extends BaseController {
    @Autowired
    private IEmailService iEmailService;
    @Autowired
    private ITemplatesService iTemplatesService;

    @ApiOperation(value = "发送信息")
    @RequiresPermissions("00200010")
    @GetMapping("/toSend")
    public ResponseBean toSend() {
        QueryWrapper<Templates> queryWrapper = new QueryWrapper();
        //类型 1-站内信、2-邮件
        queryWrapper.eq("type", "2").orderByDesc("crtm");
        List<Templates> templates = iTemplatesService.list(queryWrapper);
        return ResponseBean.Success(templates);
    }

    @ApiOperation(value = "发送信息")
    @RequiresPermissions("00200010")
    @PostMapping("/send")
    public ResponseBean send(@RequestBody EmailSmsDto emailSmsDto) {
        iEmailService.sendEmail(emailSmsDto);
        return ResponseBean.Success();
    }
}
