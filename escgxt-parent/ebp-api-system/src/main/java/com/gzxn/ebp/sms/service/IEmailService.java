package com.gzxn.ebp.sms.service;

import com.gzxn.ebp.sms.dto.EmailSmsDto;

/**
 * @Package: com.gzxn.ebp.sms.service
 * @ClassName: IEmailService
 * @Author: CodeBird
 * @Date: 2022-03-06 19:13
 * @Description: 邮件消息-服务类接口
 */
public interface IEmailService {

    void sendEmail(EmailSmsDto emailSmsDto);

}
