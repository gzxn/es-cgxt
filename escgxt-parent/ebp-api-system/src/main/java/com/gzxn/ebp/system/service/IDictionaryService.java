package com.gzxn.ebp.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gzxn.ebp.system.entity.Dictionary;

/**
 * @Package: com.gzxn.ebp.system.service
 * @ClassName: IDictionaryService
 * @Author: CodeBird
 * @Date: 2022-03-06 18:12
 * @Description: 数据字典-服务类接口
 */
public interface IDictionaryService extends IService<Dictionary> {

    Dictionary getOneByCode(String code);


}
