package com.gzxn.ebp.sms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.constant.Constant;
import com.gzxn.core.system.annotation.LogModule;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.sms.dto.SendSmsDto;
import com.gzxn.ebp.sms.entity.Templates;
import com.gzxn.ebp.sms.service.ISmsService;
import com.gzxn.ebp.sms.service.ITemplatesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Package: com.gzxn.ebp.sms.controller
 * @ClassName: SendSmsController
 * @Author: CodeBird
 * @Date: 2022-03-06 19:18
 * @Description: 发送站内信息-控制器
 */
@Api(tags = "发送站内信息")
@LogModule(value = "发送站内信息")
@RestController
@RequestMapping("/sms/sendSms")
public class SendSmsController extends BaseController {

    @Autowired
    private ISmsService iSmsService;
    @Autowired
    private ITemplatesService iTemplatesService;

    @ApiOperation(value = "发送信息")
    @RequiresPermissions("00200010")
    @GetMapping("/toSend")
    public ResponseBean toSend() {
        QueryWrapper<Templates> queryWrapper = new QueryWrapper();
        // 类型 1-站内信、2-邮件
        queryWrapper.eq("type", "1").orderByDesc("crtm");
        List<Templates> templates = iTemplatesService.list(queryWrapper);
        return ResponseBean.Success(templates);
    }

    @ApiOperation(value = "发送信息")
    @RequiresPermissions("00200000")
    @PostMapping("/send")
    public ResponseBean send(@RequestBody SendSmsDto sendSmsDto, HttpServletRequest request) {
        // 拿到用户id
        String uid = (String) request.getAttribute(Constant.REQUEST_ATTRIBUTE_UID);
        // 设置发送人id
        sendSmsDto.setSid(uid);
        if (iSmsService.sendSms(sendSmsDto)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }

    }

}