package com.gzxn.ebp.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gzxn.core.system.api.IDictionaryCommonApi;
import com.gzxn.core.system.dto.DictionarydataDto;
import com.gzxn.ebp.system.entity.Dictionarydata;
import com.gzxn.ebp.system.service.IOptionDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Package: com.gzxn.ebp.system.service.impl
 * @ClassName: DictionaryCommonApiImpl
 * @Author: CodeBird
 * @Date: 2022-03-06 18:19
 * @Description: TODO
 */
@Service
public class DictionaryCommonApiImpl implements IDictionaryCommonApi {
    @Autowired
    private IOptionDictionaryService iOptionDictionaryService;

    @Override
    public List<DictionarydataDto> listDictionaryOption(String key) {
        List<Dictionarydata> dictionarydatas = iOptionDictionaryService.listOptionDictionary(key);
        if (ObjectUtil.isNotEmpty(dictionarydatas)) {
            return dictionarydatas.stream().map(dictionarydata -> {
                return BeanUtil.copyProperties(dictionarydata, DictionarydataDto.class);
            }).collect(Collectors.toList());
        }
        return null;
    }

}
