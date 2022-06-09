package com.gzxn.config.mail;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.mail.MailAccount;
import com.gzxn.core.exception.EbpRunTimeException;
import com.gzxn.core.system.api.ISystemCommonApi;
import com.gzxn.core.system.dto.EmailSetting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Package: com.gzxn.config.mail
 * @ClassName: MailConfig
 * @Author: CodeBird
 * @Date: 2022-03-06 14:21
 * @Description: 邮件配置
 */
@Configuration
@Slf4j
public class MailConfig {
    @Autowired
    private ISystemCommonApi iSystemCommonApi;

    @Bean
    public MailAccount mailAccount() {
        if (ObjectUtil.isEmpty(iSystemCommonApi)) {
            EbpRunTimeException ebpRunTimeException = new EbpRunTimeException("MailConfig 注入入库组件出错,注入公共组件出错。");
            log.error("异常：" + ebpRunTimeException.getMessage(), ebpRunTimeException);
            throw ebpRunTimeException;
        }
        MailAccount account = new MailAccount();
        EmailSetting emailSetting = iSystemCommonApi.getEmailSetting();
        if (ObjectUtil.isNotEmpty(emailSetting)) {
            account.setHost(emailSetting.getHost());
            account.setPass(emailSetting.getPass());
            account.setPort(emailSetting.getPort());
            account.setUser(emailSetting.getUser());
            account.setFrom(emailSetting.getMailSender());
        }
        account.setAuth(true);
        // 使用ssl协议
        account.setSslEnable(true);
        return account;
    }
}
