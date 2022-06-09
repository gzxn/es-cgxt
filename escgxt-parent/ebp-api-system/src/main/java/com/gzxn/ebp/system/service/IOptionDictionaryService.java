package com.gzxn.ebp.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gzxn.ebp.system.dto.OptionDictionaryDto;
import com.gzxn.ebp.system.entity.Dictionary;
import com.gzxn.ebp.system.entity.Dictionarydata;

import java.util.List;

/**
 * @Package: com.gzxn.ebp.system.service
 * @ClassName: IOptionDictionaryService
 * @Author: CodeBird
 * @Date: 2022-03-06 18:13
 * @Description: 选项字典-服务类接口
 */
public interface IOptionDictionaryService extends IService<Dictionary> {

    boolean updateOption(OptionDictionaryDto optionDictionaryDto);

    /**
     * 根据选项字段编号，查询选项
     *
     * @param dkey
     * @return
     */
    List<Dictionarydata> listOptionDictionary(String dkey);

}