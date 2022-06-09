package com.gzxn.ebp.sms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzxn.ebp.sms.entity.Templates;
import com.gzxn.ebp.sms.mapper.TemplatesMapper;
import com.gzxn.ebp.sms.service.ITemplatesService;
import org.springframework.stereotype.Service;

/**
 * @Package: com.gzxn.ebp.sms.service.impl
 * @ClassName: TemplatesServiceImpl
 * @Author: CodeBird
 * @Date: 2022-03-06 19:16
 * @Description: 信息模版-服务实现类
 */
@Service
public class TemplatesServiceImpl extends ServiceImpl<TemplatesMapper, Templates> implements ITemplatesService {

}
