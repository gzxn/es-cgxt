package com.gzxn.ebp.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gzxn.ebp.system.dto.OptionDictionaryDto;
import com.gzxn.ebp.system.entity.Dictionarydata;

/**
 * @Package: com.gzxn.ebp.system.service
 * @ClassName: IDictionarydataService
 * @Author: CodeBird
 * @Date: 2022-03-06 18:11
 * @Description: 数据字典数据-服务类接口
 */
public interface IDictionarydataService extends IService<Dictionarydata> {

    boolean updateOptionDictionaryDto(OptionDictionaryDto optionDictionaryDto);



}

