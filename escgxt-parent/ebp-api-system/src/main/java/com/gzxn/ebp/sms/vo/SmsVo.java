package com.gzxn.ebp.sms.vo;

import com.gzxn.ebp.sms.entity.Sms;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Package: com.gzxn.ebp.sms.vo
 * @ClassName: SmsVo
 * @Author: CodeBird
 * @Date: 2022-03-06 19:11
 * @Description: 站内信息Vo
 */
@Data
@ApiModel(value = "站内信息Vo")
public class SmsVo extends Sms {

    private String sname;

}